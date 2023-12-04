package com.example.boat.config;
/*
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity

public class SecurityConfig {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable)
				.authorizeRequests(authorize -> authorize
				.antMatchers("/api/boats/**").permitAll() // Allow access to "/api/boats" for all users
				.antMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll() // Allow access to Swagger 3 resources
				.anyRequest().authenticated()
		).csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		UserDetails user = User.builder()
				.username("user")
				.password(passwordEncoder().encode("password"))
				.roles("USER")
				.build();
		return new InMemoryUserDetailsManager(user);
	}
}
*/
