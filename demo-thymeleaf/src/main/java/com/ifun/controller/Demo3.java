package com.ifun.controller;

import com.ifun.entity.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/demo3")
public class Demo3 {

    @RequestMapping(value = "/1",method = RequestMethod.GET)
    public String index(Model model) {
        List<Person> people = new ArrayList<>();
        Person p1 = new Person("李思", 21);
        Person p2 = new Person("王武", 22);
        Person p3 = new Person("赵六", 23);
        people.add(p1);
        people.add(p2);
        people.add(p3);

        model.addAttribute("people", people);

        return "demo3";
    }
}
