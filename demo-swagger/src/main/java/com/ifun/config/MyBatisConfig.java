package com.ifun.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置
 */
@Configuration
@MapperScan("com.ifun.mbg.mapper")//项目中mapper(dao)所对应的包路径
public class MyBatisConfig {
}
