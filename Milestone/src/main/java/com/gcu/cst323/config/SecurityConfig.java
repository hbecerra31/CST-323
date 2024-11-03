
package com.gcu.cst323.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * SecurityConfig is a configuration class that sets up security settings for
 * the application.
 */
@Configuration
public class SecurityConfig {

	/**
	 * Creates a PasswordEncoder bean that uses BCrypt hashing algorithm.
	 *
	 * @return a PasswordEncoder instance
	 */
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Configures the security filter chain.
	 *
	 * @param http the HttpSecurity object to configure
	 * @return a SecurityFilterChain instance
	 * @throws Exception if an error occurs while configuring the security filter
	 *                   chain
	 */
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()) // Disable CSRF protection
				.authorizeHttpRequests((auth) -> auth.requestMatchers("/user/register", "/user/login").permitAll() // Allow access to registration and login pages
						.anyRequest().authenticated()) // Require authentication for all other requests
				.formLogin(login -> login.loginPage("/user/login").loginProcessingUrl("/user/login") // Set login page
						.defaultSuccessUrl("/", true).permitAll()) // Allow access to login page
				.logout(logout -> logout.permitAll()); // Allow access
		return http.build();
	}
}
