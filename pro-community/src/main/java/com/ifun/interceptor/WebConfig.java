package com.ifun.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    SessionInterceptor sessionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionInterceptor)
                .addPathPatterns("/**");
//                .excludePathPatterns("/admin/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //配置server虚拟路径，handler为前台访问的目录，locations为files相对应的本地路径

        //允许访问static文件
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");

//        String os = System.getProperty("os.name");
//        if (os.toLowerCase().startsWith("win")) { //Windows系统
//            registry.addResourceHandler("/app_file/**")
//                    // /app_file/**表示在磁盘filePathWindow目录下的所有资源会被解析为以下的路径
//                    .addResourceLocations("file:" + filePathWindow);
//        } else { //linux和mac
//            registry.addResourceHandler("/app_file/**")
//                    .addResourceLocations("file:" + filePathLinux) ;
//        }

    }
}