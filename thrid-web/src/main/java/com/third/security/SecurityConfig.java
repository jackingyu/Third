package com.third.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoginAuthenticationFailureHandler loginAuthenticationFailureHandler;

    @Autowired
    private LoginAuthenticationSuccessHandler loginAuthenticationSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/ui/*", "/medias/**", "master", "/data", "/login", "/weixin/**", "/wx/**").permitAll() // #4
                .antMatchers("/admin/**").hasRole("ADMIN") // #6
                .anyRequest().authenticated() // 7
                .and()
                .exceptionHandling().authenticationEntryPoint(new ThirdLoginUrlAuthenticationEntryPoint("/login"))
                .accessDeniedHandler(new ThirdAccessDeniedHandler())
                .and()
                .formLogin().successHandler(loginAuthenticationSuccessHandler)
                .failureHandler(loginAuthenticationFailureHandler)
                .permitAll(); // #5
    }


    //@Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
         System.out.println("======init authentication Entry Point");
        return new ThirdLoginUrlAuthenticationEntryPoint("/login");
    }

    //@Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new ThirdAccessDeniedHandler();
    }

  //  @Bean
    public LoginUrlAuthenticationEntryPoint loginUrlAuthenticationEntryPoint() {
        return new ThirdLoginUrlAuthenticationEntryPoint("/login");
    }

}
