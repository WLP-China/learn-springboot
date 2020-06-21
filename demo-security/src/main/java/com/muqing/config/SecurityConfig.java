package com.muqing.config;

import com.muqing.filter.TokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * spring security配置
 */
//用 @EnableWebSecurity注解 并继承WebSecurityConfigurerAdapter的类，就构成了Spring Security的配置。
//WebSecurityConfigurerAdapter 提供了一种便利的方式去创建 WebSecurityConfigurer的实例，只需要重写 WebSecurityConfigurerAdapter 的方法，即可配置拦截什么URL、设置权限等安全控制。
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
    private AccessDeniedHandler accessDeniedHandler;
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
                        "/pages/**",// /**/*.html
                        "/css/**",  // /**/*.css
                        "/js/**",   // /**/*.js
                        "/fonts/**",
                        "/img/**",
                        "/statics/**"
//                        "/v2/api-docs/**",
//                        "/swagger-resources/**",
//                        "/webjars/**",
//                        "/druid/**",
                ).permitAll()
                /*
                 * Filter拦截请求两次的问题
                 * 跨域请求会先进行一次options请求。跨域的post的请求会验证两次，get不会。
                 * 网上的解释是，post请求第一次是预检请求，Request Method： OPTIONS。
                 * */
                //.antMatchers(HttpMethod.OPTIONS).permitAll()//不拦截options请求

                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated();
                //测试时全部运行访问
                //.antMatchers("/**").permitAll();

        httpSecurity
                .formLogin()//允许表单登录
                .loginProcessingUrl("/login")//指定登录处理的URL，也就是用户名、密码表单提交的目的路径
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .and()
                .exceptionHandling()//添加自定义未授权和未登录结果返回
                .accessDeniedHandler(accessDeniedHandler)
                .authenticationEntryPoint(authenticationEntryPoint);

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
/*      auth.inMemoryAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder())//密码加密
                .withUser("admin")
                .password(new BCryptPasswordEncoder().encode("123456"))
                .roles("admn","normal");//内存中初始化认证信息
        */
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder());
    }

    //密码生成策略
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();//使用BCrypt密码编码器
        /*
         * BCrypt是一种跨平台的文件加密工具。使用的是Blowfish加密算法
         * 由它加密的文件可在所有支持的操作系统和处理器上进行转移。它的口令必须是8至56个字符，并将在内部被转化为448位的密钥。
         * */
    }
}
/* 密码加密
方式1：通过AuthenticationManagerBuilder指定
    auth.inMemoryAuthentication()
            .passwordEncoder(new BCryptPasswordEncoder())
            .withUser("admin")
            .password(new BCryptPasswordEncoder().encode("123456"))
            .roles();
方式2：通过@Bean注入指定的PasswordEncoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();//使用BCrypt密码编码器
    }
    之后需要修改configure的密码加密：
    auth.inMemoryAuthentication()
            .withUser("admin")
            .password(passwordEncoder().encode("123456"))
            .roles();
* */
/* spring security 注解@EnableGlobalMethodSecurity的三种开启注解方式(可以同时启用多个类型的注解)

Spring Security默认是禁用注解的，要想开启注解，需要：
    1.在继承WebSecurityConfigurerAdapter的类上加@EnableGlobalMethodSecurity注解，
    2.在该类中将AuthenticationManager定义为Bean。

一、JSR-250 注解
@DenyAll 拒绝所有访问
@RolesAllowed({"USER", "ADMIN"}) //方法只要具有"USER", "ADMIN"任意一种权限就可以访问。这里可以省略前缀ROLE_，实际的权限可能是ROLE_ADMIN
@PermitAll 允许所有访问

二、securedEnabled 安全注解
@Secured注解是用来定义业务方法的安全配置。在需要安全[角色/权限等]的方法上指定 @Secured，并且只有那些角色/权限的用户才可以调用该方法。
@Secured缺点是不支持Spring EL表达式。不够灵活。并且指定的角色必须以ROLE_开头，不可省略。
eg:
    @Secured("ROLE_dba")
    @Secured({"ROLE_admin", "ROLE_dba"})//注释的方法只能够被拥有admin 或者dba 权限的用户调用,@Secured注解不能实现“AND”条件

三、prePostEnabled 前置注解
该注解更适合方法级的安全,也支持Spring EL表达式语言，提供了基于表达式的访问控制。参见[常见内置表达式]了解支持表达式的完整列表

启用prePostEnabled后，提供有四个注解：
@PreAuthorize：进入方法之前验证授权。可以将登录用户的roles参数传到方法中验证
而且这里可以调用方法的参数，也可以得到参数值

    这是利用JAVA8的参数名反射特性，如果没用JAVA8，那么也可以利用Spring Security的@P标注参数，或者Spring Data的@Param标注参数。eg:
    @PreAuthorize("#userId == authentication.principal.userId or hasAuthority(‘ADMIN’)")
    void changePassword(@P("userId") long userId ){  }
    这里表示在changePassword方法执行之前，判断方法参数userId的值是否等于principal中保存的当前用户的userId，或者当前用户是否具有ROLE_ADMIN权限
eg:

    @PreAuthorize ("hasAnyRole('user')") // 只能user角色可以访问
    @PreAuthorize ("hasAnyRole('user') or hasAnyRole('admin')")// user 角色或者 admin 角色都可访问
    @PreAuthorize ("hasAnyRole('user') and hasAnyRole('admin')")// 同时拥有 user 和 admin 角色才能访问

    public interface UserService {
        @PreAuthorize("#id < 10")// 限制只能查询 id 小于 10 的用户
        User findById(int id);

        @PreAuthorize("principal.username.equals(#username)")// 只能查询自己的信息
        User find(String username);

        @PreAuthorize("#user.name.equals('abc')")// 限制只能新增用户名称为abc的用户
        void add(User user)

        @PostAuthorize ("returnObject.type == authentication.name")
        User findById(int id);

        @PreAuthorize("hasRole('ADMIN')")
        void updateUser(User user);

        @PreAuthorize("hasRole('ADMIN') AND hasRole('DBA')")
        void deleteUser(int id);
    }

@PostAuthorize：在方法执行后再进行权限验证。 适合验证带有返回值的权限。表达式变量returnObject表示返回的对象。
eg:
    // 查询到用户信息后，再验证用户名是否和登录用户名一致
    @PostAuthorize("returnObject.name == authentication.name")
    public User getUser(String name){
        return userService.getUser(name);
    }
    // 验证返回的数是否是偶数
    @PostAuthorize("returnObject % 2 == 0")
    public Integer test(){
        // ...
        return id;
    }
    // 返回对象的userId是否等于当前对象的userId或者返回对象使用用于ADMIN权限
    @PostAuthorize
    User getUser("returnObject.userId == authentication.principal.userId or hasPermission(returnObject, 'ADMIN')");

@PreFilter：对集合类型的参数执行过滤，移除结果为false的元素（很少会用到，与分页技术不兼容）
    EL变量filterObject表示参数，如有多个参数，使用filterTarget注解参数。只有方法参数是集合或数组才行。
    // 指定过滤的参数，过滤偶数
    @PreFilter(filterTarget="ids", value="filterObject%2==0")
    public void delete(List<Integer> ids, List<String> username)

@PostFilter：对集合类型的返回值进行过滤，移除结果为false的元素
    @PostFilter("filterObject.id%2==0")
    public List<User> findAll(){
        ...
        return userList;
    }

----------------[常见内置表达式]-------------------
https://docs.spring.io/spring-security/site/docs/4.0.1.RELEASE/reference/htmlsingle/#el-common-built-in

表达式                   描述
hasRole([role])             当前用户是否拥有指定角色。
hasAnyRole([role1,role2])   当前用户是否拥有指定角色中的任意一个
hasAuthority([auth])        等同于hasRole
hasAnyAuthority([auth1,auth2])  等同于hasAnyRole
principle               代表当前用户的principle对象
authentication          直接从SecurityContext获取的当前Authentication对象
permitAll               总是返回true，表示允许所有的
denyAll                 总是返回false，表示拒绝所有的
isAnonymous()           当前用户是否是一个匿名用户
isRememberMe()          表示当前用户是否是通过“Remember-Me(记住我)”自动登录的
isAuthenticated()       表示当前用户是否已经登录认证成功了
isFullyAuthenticated()  如果当前用户既不是一个匿名用户，同时又不是通过Remember-Me自动登录的，则返回true。
hasPermission(Object target, Object permission)
    用户是否可以访问给定权限的给定目标。例如，hasPermission(domainObject, 'read')
hasPermission(Object targetId, String targetType, Object permission)
    用户是否可以访问给定权限的给定目标。例如，hasPermission(1, 'com.example.domain.Message', 'read')
* */