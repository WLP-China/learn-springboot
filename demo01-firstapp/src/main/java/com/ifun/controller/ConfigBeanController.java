package com.muqing.controller;

import com.muqing.entity.ConfigBean;
import com.muqing.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableConfigurationProperties({ConfigBean.class, User.class})
public class ConfigBeanController {

    /**
     * 将配置文件的属性赋给实体类
     */
    @Autowired
    ConfigBean configBean;

    @RequestMapping(value = "/configBean")
    public String configBeanTest() {
        return configBean.getGreeting() + " | " + configBean.getName() + " | " + configBean.getUuid() + " | " + configBean.getMax();
    }

    /**
     * 将自定义配置文件的属性赋给实体类
     */
    @Autowired
    User user;

    @RequestMapping(value = "/user")
    public String user() {
        return user.getName() + " | " + user.getAge();
    }
}
