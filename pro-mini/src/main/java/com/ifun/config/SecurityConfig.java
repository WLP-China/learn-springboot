package com.ifun.config;

import com.ifun.filter.TokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * spring security配置
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//开启方法级别安全控制，并使用prePostEnabled注解
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private TokenFilter tokenFilter;

    /**
     * 配置需要拦截的url路径、jwt过滤器及出异常后的处理器
     *
     * @param httpSecurity
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // 屏蔽CSRF控制.(spring security为防止CSRF（Cross-site request forgery跨站请求伪造）的发生，限制了除了get以外的大多数方法)
        httpSecurity.csrf().disable();

        // 基于token，不需要session
        httpSecurity.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);//STATELESS: SpringSecurity将不会创建Session，也不使用Session

        httpSecurity.authorizeRequests()
                // 允许对于网站静态资源的无授权访问
                .antMatchers(
                        //HttpMethod.GET,
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/pages/**",
                        "/css/**",
                        "/js/**",
                        "/fonts/**",
                        "/img/**",
//                        "/layui/**",
//                        "/v2/api-docs/**",
//                        "/swagger-resources/**",
//                        "/webjars/**",
//                        "/druid/**",
                        "/statics/**").permitAll()
                .anyRequest().authenticated();

        httpSecurity
                .formLogin()//允许表单登录
                .loginProcessingUrl("/login")//指定登录处理的URL，也就是用户名、密码表单提交的目的路径
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .and()
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);

        httpSecurity
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(logoutSuccessHandler);

        // 解决不允许显示在iframe的问题
        httpSecurity.headers().frameOptions().disable();
        // 禁用缓存
        httpSecurity.headers().cacheControl();
        // 添加tokenFilter来验证token有效性
        // 在用户名和密码校验前添加的过滤器，如果有token，会自行根据token信息进行登录
        httpSecurity.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * 配置UserDetailsService及PasswordEncoder
     * .userDetailsService : SpringSecurity定义的核心接口，用于根据用户名获取用户信息，需要自行实现
     * .passwordEncoder : SpringSecurity定义的用于对密码进行编码及比对的接口
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder());
    }

    //密码生成策略
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
