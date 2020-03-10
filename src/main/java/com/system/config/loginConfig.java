package com.system.config;


import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class loginConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        //请求授权规则
        http.authorizeRequests()
                .antMatchers("/").permitAll();

        //没权限转调至login界面
        http.formLogin();


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/bootstrap/**")
                .antMatchers("/css/**")
                .antMatchers("/js/**");
        //忽略静态资源
    }
}
