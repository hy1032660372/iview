package com.hy.spring.cloud.account.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName WebSecurityConfig
 * @Description TODO
 * @Author P1316771
 * @Date 11/29/2018
 **/

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint((request,response,authException)->response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                .authorizeRequests().antMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and().logout();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

}
