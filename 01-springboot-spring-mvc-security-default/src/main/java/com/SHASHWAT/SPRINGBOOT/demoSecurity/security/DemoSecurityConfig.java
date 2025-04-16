package com.SHASHWAT.SPRINGBOOT.demoSecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

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
}
