package com.edocapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Order(1)
public class SecurityConfigPatient {

	@Bean
	public UserDetailsService getUserDetailsService() {
		return new PatientDetailServiceImpl();
	}

//    @Bean
//    public PasswordEncoder passwordEncoder1() {
//        return NoOpPasswordEncoder.getInstance();
//    }

	@Bean
	public BCryptPasswordEncoder passwordEncoder1() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider1() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(getUserDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder1());

		return authProvider;
	}

	@Bean
	public SecurityFilterChain filterChain1(HttpSecurity http) throws Exception {
		http.authenticationProvider(authenticationProvider1());
		http.authorizeRequests().antMatchers("/").permitAll();

		http.antMatcher("/patient/**")
		.authorizeRequests()
		.anyRequest()
		.authenticated()
		.and()
		.formLogin()
				.loginPage("/patient/login")
				.usernameParameter("email")
				.loginProcessingUrl("/patient/login")
				.defaultSuccessUrl("/patient/home").permitAll()
		.and()
		.logout()
			.logoutUrl("/patient/logout")
			.logoutSuccessUrl("/");

		return http.build();

	}
}