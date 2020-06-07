package com.muqing.controller;

import com.muqing.entity.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/demo2")
public class Demo2 {

    @RequestMapping(value = "/1",method = RequestMethod.GET)
    public String index(Model model) {
        Person p = new Person("章三", 20);
        model.addAttribute("person", p);
        return "demo2";
    }

    @RequestMapping(value = "2", method = RequestMethod.GET)
    public String getsubmitPage( ) {
        return "submit";
    }
    @RequestMapping(value = "/submit",method = RequestMethod.POST)
    public String form (@ModelAttribute Person person){
        ModelMap map = new ModelMap();
        person.getName();
        person.getAge();
        map.addAttribute("person", person);
        return "demo2";
    }
}
