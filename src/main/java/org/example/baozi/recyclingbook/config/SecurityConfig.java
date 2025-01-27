package org.example.baozi.recyclingbook.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
// security配置，可以使用工具进行接口调试
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/student/login").permitAll()  // 允许所有人访问登录接口
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin()
                ;
    }

}
