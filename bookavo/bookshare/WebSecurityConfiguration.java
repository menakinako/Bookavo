package com.bookavo.bookshare;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    
    

    @Bean
    public UserDetailsService userDetailsService(){
        return new  CustomerUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return  authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)  throws Exception{
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
            .antMatchers("/").authenticated()
            .anyRequest().permitAll()
            .and()
            .formLogin()
                 .loginPage("/login.html")
                 
                 .defaultSuccessUrl("/index.html",true)
                 .failureUrl("/login.html?error=true")
                 .permitAll()
                 .and()
               .logout() 
               
                  .permitAll()
                  .and().exceptionHandling().accessDeniedPage("/accessdenied")
                  .and();
                  
                  

    }

}
