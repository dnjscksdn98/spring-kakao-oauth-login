package com.study.SpringBootOAuth2LoginAPI.config;

import com.study.SpringBootOAuth2LoginAPI.oauth2.CustomOAuth2SuccessHandler;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                    .disable()
                .headers()
                    .frameOptions().disable()
                    .and()
                .authorizeRequests()
                    .antMatchers("/h2-console/**").permitAll()
                    .antMatchers("/oauth2/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .oauth2Login()  // OAuth2 Login 활성화
                    .successHandler(new CustomOAuth2SuccessHandler());
    }
}
