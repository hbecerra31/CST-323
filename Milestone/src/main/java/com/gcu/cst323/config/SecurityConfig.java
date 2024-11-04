
package com.gcu.cst323.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.JdbcUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;

/**
 * SecurityConfig is a configuration class that sets up security settings for
 * the application.
 */
@Configuration
//@EnableWebSecurity
public class SecurityConfig {
//
//	/**
//	 * Creates a PasswordEncoder bean that uses BCrypt hashing algorithm.
//	 *
//	 * @return a PasswordEncoder instance
//	 */
//	@Bean
//	PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//
//	@Bean
//	UserDetailsService userDetailsService(DataSource dataSource) {
//		// This will use Spring's default schema or you can customize it
//		JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
//		return manager;
//	}
//
//	/**
//	 * Configures the security filter chain.
//	 *
//	 * @param http the HttpSecurity object to configure
//	 * @return a SecurityFilterChain instance
//	 * @throws Exception if an error occurs while configuring the security filter
//	 *                   chain
//	 */
//	@Bean
//	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		http.csrf(csrf -> csrf.disable())
//				.authorizeHttpRequests(auth -> auth
//					.requestMatchers("/users/register", "/users/login").permitAll()
//					.anyRequest().authenticated())
//				.formLogin(login -> login
//					.loginPage("/users/login")
//					.defaultSuccessUrl("/", true)
//					.permitAll())
//				.logout(logout -> logout.permitAll());
//		return http.build();
//	}
}
