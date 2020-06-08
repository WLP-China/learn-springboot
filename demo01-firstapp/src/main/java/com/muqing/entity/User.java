package com.muqing.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * test:将自定义配置文件resources/application.yml的属性值赋给实体类
 */
//需加以下三个注解
@Configuration
@PropertySource(value = "classpath:test.properties")
@ConfigurationProperties(prefix = "user.ming")
public class User {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
