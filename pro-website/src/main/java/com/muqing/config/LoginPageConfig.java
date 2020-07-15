package com.muqing.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class LoginPageConfig {

//    @RequestMapping("/platform")
    @RequestMapping("/")
    public RedirectView platformPage() {
        return new RedirectView("/login.html");
    }

//    @RequestMapping("/")
//    public RedirectView websiteIndexPage() {
//        return new RedirectView("/website/index.html");
//    }
}
