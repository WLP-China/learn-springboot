package com.ifun.controller;

import com.ifun.component.GiteeProvider;
import com.ifun.component.GithubProvider;
import com.ifun.dto.AccessTokenDTO;
import com.ifun.dto.GiteeAccessTokenDTO;
import com.ifun.dto.UserDTO;
import com.ifun.mapper.UserMapper;
import com.ifun.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Create by iFun on 2020/03/30
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;
    @Autowired
    private GiteeProvider giteeProvider;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired(required = false)
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String githubCallback(@RequestParam(name = "code") String code,
                                 @RequestParam(name = "state") String state,
                                 HttpServletRequest request) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        UserDTO githubUser = githubProvider.getUser(accessToken);
        if (githubUser != null && githubUser.getId() != null) {
            //登陆成功，写cookie和session
            request.getSession().setAttribute("user", githubUser);

            User user = new User();
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setName(githubUser.getName());
            user.setBio(githubUser.getBio());
            user.setAvatarUrl(githubUser.getAvatar_url());
            user.setToken(UUID.randomUUID().toString());
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insertUser(user);

            return "redirect:/";
        } else {
            //登陆失败，重新登陆
            return "redirect:/login";
        }
    }

    @GetMapping("/callback/gitee")
    public String giteeCallback(@RequestParam(name = "code") String code,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        GiteeAccessTokenDTO accessTokenDTO = new GiteeAccessTokenDTO();
        accessTokenDTO.setGrant_type("authorization_code");
        accessTokenDTO.setClient_id("12ae6cd285a5695182048a47a0187e084dba672a01cd73e9aadbec7f573927b3");
        accessTokenDTO.setClient_secret("25741fb52a551fea8baeea956bc1188e8d3856c251c56da0dece41af9f404791");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri("http://127.0.0.1:8080/callback/gitee");

        String accessToken = giteeProvider.getAccessToken(accessTokenDTO);
        UserDTO giteeUser = giteeProvider.getUser(accessToken);
        if (giteeUser != null) {
            //登陆成功，写cookie和session
//            request.getSession().setAttribute("user", giteeUser);
            String token = UUID.randomUUID().toString();
            User user = new User();
            user.setAccountId(String.valueOf(giteeUser.getId()));
            user.setName(giteeUser.getName());
            user.setBio(giteeUser.getBio());
            user.setAvatarUrl(giteeUser.getAvatar_url());
            user.setToken(token);
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insertUser(user);

            Cookie cookie = new Cookie("token", token);
            cookie.setMaxAge(3600);
            cookie.setPath("/");
            response.addCookie(cookie);

            return "redirect:/";
        } else {
            //登陆失败，重新登陆
            return "redirect:/login";
        }
    }
}
