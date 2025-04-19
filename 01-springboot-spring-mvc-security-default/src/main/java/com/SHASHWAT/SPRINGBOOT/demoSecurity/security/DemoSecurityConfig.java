package com.SHASHWAT.SPRINGBOOT.demoSecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {
    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){


        UserDetails john = User.builder()
                .username("John")
                .password("{noop}test123")
                .roles("Employee")
                .build();


        UserDetails mary = User.builder()
                .username("mary")
                .password("{noop}test123")
                .roles("Manager","Employee")
                .build();

        UserDetails susan = User.builder()
                .username("susan")
                .password("{noop}test123")
                .roles("Employee","Employee","Admin")
                .build();


        return new InMemoryUserDetailsManager(john,mary,susan);

    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(configurer->
                configurer
                        .requestMatchers("/").hasRole("Employee")
                        .requestMatchers("/leaders/**").hasRole("Manager")
                        .requestMatchers("/systems/**").hasRole("Admin")
                        .anyRequest().authenticated()
        )
                .formLogin(form->
                        form
                                .loginPage("/showMyLoginPage")
                                .loginProcessingUrl("/authenticateTheUser")
                                .permitAll()
                )
                .logout(logout-> logout.permitAll()
                );

        return http.build();
    }
}
