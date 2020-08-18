package com.muqing.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.muqing.common.page.table.PageTableArgumentResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.SimpleDateFormat;
import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * CORS全局配置 跨域支持
     *
     * @return
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("*") //允许跨域的域名，可以用*表示允许任何域名使用
                        .allowedMethods("*") //允许任何方法（post、get等）
                        .allowedHeaders("*") //允许任何请求头
                        .allowCredentials(true) //带上cookie信息
                        .exposedHeaders(HttpHeaders.SET_COOKIE).maxAge(3600L); //maxAge(3600)表明在3600秒内，不需要再发送预检验请求，可以缓存该结果
            }
        };
    }

    /**
     * datatable分页解析
     *
     * @return
     */
    @Bean
    public PageTableArgumentResolver tableHandlerMethodArgumentResolver() {
        return new PageTableArgumentResolver();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(tableHandlerMethodArgumentResolver());
    }

    /**
     * 定义时间格式转换器.用于返回json数据时候日期格式化
     *
     * 改为配置文件文件方式：application.yml
     * spring:jackson:date-format: yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
//    @Bean
//    public MappingJackson2HttpMessageConverter jackson2HttpMessageConverter() {
//        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
//        converter.setObjectMapper(mapper);
//        return converter;
//    }
//
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        //将自定义的时间格式转换器添加到转换器列表中,jackson格式化时遇到Date类型就会转换成所定义的格式
//        converters.add(jackson2HttpMessageConverter());
//    }
}
