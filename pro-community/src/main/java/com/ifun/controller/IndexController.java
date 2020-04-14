package com.ifun.controller;

import com.ifun.dto.QuestionDTO;
import com.ifun.mapper.QuestionMapper;
import com.ifun.mapper.UserMapper;
import com.ifun.model.User;
import com.ifun.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Create by iFun on 2020/03/30
 */
@Controller
public class IndexController {

    @Autowired(required = false)
    private UserMapper userMapper;
    @Autowired(required = false)
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model) {
        Cookie[] cookies = request.getCookies();
        if(cookies !=null){
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                }
            }
        }
        //查询列表
        List<QuestionDTO> questionList=questionService.list();
        model.addAttribute("questions", questionList);

        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "index";
    }
}
