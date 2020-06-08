package com.muqing.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * test: 获取配置文件resources/application.yml的属性值
 */
@RestController
public class ConfigController {

    //读取配置文件的值只需要加@Value("${属性名}")
    @Value("${configTest.name}")
    private String name;

    @Value("${configTest.age}")
    private String age;

    @RequestMapping("/configTest")
    public String configTest() {
        return name + " | " + age;
    }
}
