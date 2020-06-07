package com.muqing.controller;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "Hello Spring Boot!";
    }

    //测试springboot在启动的时候为我们注入了哪些bean
//    @Bean
//    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//        return args -> {
//            System.out.println("===================================");
//
//            String[] beanNames = ctx.getBeanDefinitionNames();
//            Arrays.sort(beanNames);
//            for (String beanName : beanNames) {
//                System.out.println(beanName);
//            }
//        };
//    }
}

/*
@RestController
    `返回json
    Spring4之后新加入的注解，原来返回json需要@ResponseBody和@Controller配合。
    即@RestController是@ResponseBody和@Controller的组合注解。

@RequestMapping
    `配置url映射
    此注解即可以作用在控制器的某个方法上，也可以作用在此控制器类上
    当控制器在类级别上添加@RequestMapping注解时，这个注解会应用到控制器的所有处理器方法上。
    处理器方法上的@RequestMapping注解会对类级别上的@RequestMapping的声明进行补充。
*/
