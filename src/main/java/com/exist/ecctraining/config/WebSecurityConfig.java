package com.exist.ecctraining.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity
public class WebSecurityConfig {

    //default user and password for each role
    @Bean
    public InMemoryUserDetailsManager configure() throws Exception {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("user")
                .roles("USER")
                .build();
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }
    @Bean
    public SecurityFilterChain employeeAndTicketsFilterChain(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .authorizeHttpRequests(request ->
                        request.requestMatchers("/api/employee/all",
                                        "/api/employee/find/**",
                                        "/api/ticket/all",
                                        "/api/ticket/find/**")
                                .permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/employee/add",
                                        "/api/ticket/create")
                                .hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PATCH,"/api/employee/update/**",
                                        "/api/employee/assign/**",
                                        "/api/ticket/update/**")
                                .hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/employee/delete/**",
                                        "/api/ticket/delete/**")
                                .hasRole("ADMIN")
                                .anyRequest()
                                .authenticated())
                .httpBasic();
        return http.build();


    }

}

