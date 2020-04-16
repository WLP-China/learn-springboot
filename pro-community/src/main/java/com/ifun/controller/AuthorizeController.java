package com.ifun.controller;

import com.ifun.component.GiteeProvider;
import com.ifun.component.GithubProvider;
import com.ifun.dto.AccessTokenDTO;
import com.ifun.dto.GiteeAccessTokenDTO;
import com.ifun.dto.UserDTO;
import com.ifun.model.User;
import com.ifun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
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

    @Autowired
    private UserService userService;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Value("${gitee.client.id}")
    private String giteeClientId;
    @Value("${gitee.client.secret}")
    private String giteeClientSecret;
    @Value("${gitee.redirect.uri}")
    private String giteeRedirectUri;

    @GetMapping("/callback")
    public String githubCallback(@RequestParam(name = "code") String code,
                                 @RequestParam(name = "state") String state,
                                 HttpServletResponse response) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);

        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        UserDTO githubUser = githubProvider.getUser(accessToken);
        if (githubUser != null && githubUser.getId() != null) {
            //登陆成功，写session
//            request.getSession().setAttribute("user", githubUser);

            String token = UUID.randomUUID().toString();
            User user = new User();
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setName(githubUser.getName());
            user.setBio(githubUser.getBio());
//            user.setAvatarUrl(githubUser.getAvatar_url());
            user.setAvatarUrl(githubUser.getAvatarUrl());
            user.setToken(token);
            userService.insertOrUpdateUser(user);

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

    @GetMapping("/callback/gitee")
    public String giteeCallback(@RequestParam(name = "code") String code,
                                HttpServletResponse response) {
        GiteeAccessTokenDTO accessTokenDTO = new GiteeAccessTokenDTO();
        accessTokenDTO.setGrant_type("authorization_code");
        accessTokenDTO.setClient_id(giteeClientId);
        accessTokenDTO.setClient_secret(giteeClientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(giteeRedirectUri);

        String accessToken = giteeProvider.getAccessToken(accessTokenDTO);
        UserDTO giteeUser = giteeProvider.getUser(accessToken);
        if (giteeUser != null && giteeUser.getId() != null) {
            String token = UUID.randomUUID().toString();
            User user = new User();
            user.setAccountId(String.valueOf(giteeUser.getId()));
            user.setName(giteeUser.getName());
            user.setBio(giteeUser.getBio());
            user.setAvatarUrl(giteeUser.getAvatarUrl());
            user.setToken(token);
            userService.insertOrUpdateUser(user);

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
