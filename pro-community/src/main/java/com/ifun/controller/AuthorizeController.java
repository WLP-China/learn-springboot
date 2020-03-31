package com.ifun.controller;

import com.ifun.component.GithubProvider;
import com.ifun.dto.AccessTokerDTO;
import com.ifun.dto.GithubUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Create by iFun on 2020/03/30
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state) {
        AccessTokerDTO accessTokerDTO = new AccessTokerDTO();
        accessTokerDTO.setClient_id("d652f5db17b798fc512f");
        accessTokerDTO.setClient_secret("f60ed458909e26513a8fc8e432056126f9f2dc81");
        accessTokerDTO.setCode(code);
        accessTokerDTO.setRedirect_uri("http://localhost:8080/callback");
        accessTokerDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokerDTO);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user.getId());
        return "index";
    }

}
