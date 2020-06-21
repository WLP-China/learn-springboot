package com.muqing.config;

import com.muqing.common.api.CommonResult;
import com.muqing.common.utils.ResponseUtil;
import com.muqing.dto.LoginUserDTO;
import com.muqing.dto.ResponseInfoDTO;
import com.muqing.dto.TokenDTO;
import com.muqing.filter.TokenFilter;
import com.muqing.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * spring security处理器
 */
@Configuration
public class SecurityHandlerConfig {

    @Autowired
    private TokenService tokenService;

    /**
     * 登陆成功，返回Token
     *
     * @return
     */
    @Bean
    public AuthenticationSuccessHandler loginSuccessHandler() {
        return new AuthenticationSuccessHandler() {

            @Override
            public void onAuthenticationSuccess(HttpServletRequest request,
                                                HttpServletResponse response,
                                                Authentication authentication) throws IOException, ServletException {
                LoginUserDTO loginUser = (LoginUserDTO) authentication.getPrincipal();

                TokenDTO token = tokenService.saveToken(loginUser);
//                ResponseUtil.responseJson(response, HttpStatus.OK.value(), token);
                ResponseUtil.responseJson(response, HttpStatus.OK.value(), CommonResult.success(token));
            }
        };
    }

    /**
     * 登陆失败
     *
     * @return
     */
    @Bean
    public AuthenticationFailureHandler loginFailureHandler() {
        return new AuthenticationFailureHandler() {

            @Override
            public void onAuthenticationFailure(HttpServletRequest request,
                                                HttpServletResponse response,
                                                AuthenticationException e) throws IOException, ServletException {
                String msg = null;
                if (e instanceof BadCredentialsException) {
                    msg = "账户或密码错误";
                } else {
                    msg = e.getMessage();
                }
//                ResponseInfoDTO info = new ResponseInfoDTO(HttpStatus.UNAUTHORIZED.value() + "", msg);
//                ResponseUtil.responseJson(response, HttpStatus.UNAUTHORIZED.value(), info);
                ResponseUtil.responseJson(response, HttpStatus.OK.value(), CommonResult.failed(msg));
            }
        };
    }

    /**
     * 未登录
     *
     * @return
     */
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new AuthenticationEntryPoint() {

            @Override
            public void commence(HttpServletRequest request,
                                 HttpServletResponse response,
                                 AuthenticationException e) throws IOException, ServletException {
//                ResponseInfoDTO info = new ResponseInfoDTO(HttpStatus.UNAUTHORIZED.value() + "", "请先登录");
//                ResponseUtil.responseJson(response, HttpStatus.UNAUTHORIZED.value(), info);
                ResponseUtil.responseJson(response, HttpStatus.OK.value(), CommonResult.unauthorized(e.getMessage()));
            }
        };
    }

    /**
     * 权限不足
     *
     * @return
     */
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new AccessDeniedHandler() {
            @Override
            public void handle(HttpServletRequest request,
                               HttpServletResponse response,
                               AccessDeniedException e) throws IOException, ServletException {
//                ResponseInfoDTO info = new ResponseInfoDTO(HttpStatus.UNAUTHORIZED.value() + "", e.getMessage());
//                ResponseUtil.responseJson(response, HttpStatus.UNAUTHORIZED.value(), info);
                ResponseUtil.responseJson(response, HttpStatus.OK.value(), CommonResult.forbidden(e.getMessage()));

            }
        };
    }

    /**
     * 退出处理
     *
     * @return
     */
    @Bean
    public LogoutSuccessHandler logoutSussHandler() {
        return new LogoutSuccessHandler() {

            @Override
            public void onLogoutSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
//                ResponseInfoDTO info = new ResponseInfoDTO(HttpStatus.OK.value() + "", "退出成功");

                String token = TokenFilter.getToken(request);
                tokenService.deleteToken(token);

//                ResponseUtil.responseJson(response, HttpStatus.OK.value(), info);
                ResponseUtil.responseJson(response, HttpStatus.OK.value(), CommonResult.success(null, "退出成功"));
            }
        };
    }
}
