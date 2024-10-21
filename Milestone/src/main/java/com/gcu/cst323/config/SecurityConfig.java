package com.gcu.cst323.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/register", "/login/login", "/api/tasks/**").permitAll() // Allow public access to specific endpoints
                .anyRequest().authenticated() // Require authentication for all other requests
            )
            .formLogin(form -> form
                .loginPage("/login") // Use a custom login page (make sure you have a mapping for /login)
                .loginProcessingUrl("/login") // URL to submit the username and password
                .defaultSuccessUrl("/", true) // Redirect to the homepage after successful login
                .permitAll() // Allow all users to see the login page
            )
            .logout(logout -> logout
                .logoutUrl("/logout") // URL to trigger logout
                .logoutSuccessUrl("/login?logout") // Redirect to login page after logout
                .permitAll() // Allow all users to access the logout URL
            );
        return http.build();
    }
}

