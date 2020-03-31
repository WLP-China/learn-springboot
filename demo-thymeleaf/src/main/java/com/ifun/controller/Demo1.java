package com.ifun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/demo1")
public class Demo1 {

    @RequestMapping(value = "/1",method = RequestMethod.GET)
    public String demo1(ModelMap modelMap) {
        // 加入一个属性，用来在模板中读取
        modelMap.addAttribute("host", "www.baidu.com");

        return "demo1";// 返回模板文件的名称，对应src/main/resources/templates/index.html
    }

    @RequestMapping(value = "/2",method = RequestMethod.GET)
    public ModelAndView demo2() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("host", "www.1234.com");
        modelAndView.setViewName("demo1");
        return modelAndView;
    }
}
