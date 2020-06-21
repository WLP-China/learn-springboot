package com.muqing.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.muqing.dto.LoginUserDTO;
import com.muqing.service.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Token过滤器
 */
@Component
public class TokenFilter extends OncePerRequestFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(TokenFilter.class);

    public static final String TOKEN_KEY = "token";
    private static final Long MINUTES_10 = 10 * 60 * 1000L;

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = getToken(request);
        if (StringUtils.isNotBlank(token)) {
            LoginUserDTO loginUserDTO = tokenService.getLoginUser(token);
            if (loginUserDTO != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                LOGGER.info("Checking authentication for user:{}", loginUserDTO.getUsername());
                loginUserDTO = checkLoginTime(loginUserDTO);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(loginUserDTO, null, loginUserDTO.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                LOGGER.info("Authentication Success,setting security context. user:{}", loginUserDTO.getUsername());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                LOGGER.info("Authentication Failure : invalid token");
            }
        }
        filterChain.doFilter(request, response);
    }

    /**
     * 根据 请求参数(Params) 或 请求头(headers) 获取token
     *
     * @param request
     * @return
     */
    public static String getToken(HttpServletRequest request) {
        String token = request.getHeader(TOKEN_KEY);
        if (StringUtils.isBlank(token)) {
            token = request.getParameter(TOKEN_KEY);
        }
        return token;
    }

    /**
     * 校验时间
     * 过期时间与当前时间对比，临近过期10分钟内的话，自动刷新缓存
     *
     * @param loginUserDTO
     * @return
     */
    private LoginUserDTO checkLoginTime(LoginUserDTO loginUserDTO) {
        long expireTime = loginUserDTO.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= MINUTES_10) {
            String token = loginUserDTO.getToken();
            loginUserDTO = (LoginUserDTO) userDetailsService.loadUserByUsername(loginUserDTO.getUsername());
            loginUserDTO.setToken(token);
            tokenService.refresh(loginUserDTO);
        }
        return loginUserDTO;
    }
}
