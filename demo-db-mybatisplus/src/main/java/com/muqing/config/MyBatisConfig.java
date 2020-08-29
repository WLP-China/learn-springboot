package com.muqing.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.muqing.mapper"})
public class MyBatisConfig {
}