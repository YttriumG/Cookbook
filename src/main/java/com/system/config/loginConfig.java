package com.system.config;


import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class loginConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        //请求授权规则
        http.authorizeRequests()
                .antMatchers("/").permitAll();

        //没权限转调至login界面
        http.csrf().disable()
            .formLogin()
                .loginPage("/")
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/")
            .and()
            .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/sys.html").hasRole("admin");

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/bootstrap/**","/all/**","/register/**");
        //忽略静态资源
    }
}
