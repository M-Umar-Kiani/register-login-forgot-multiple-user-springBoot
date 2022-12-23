package com.edocapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@Order(2)
public class SecurityConfigDoctor {
	
	@Bean
    public UserDetailsService userDetailsService2() {
        return new DoctorDetailServiceImpl();
    }
 
//    @Bean
//    public PasswordEncoder passwordEncoder2() {
//        return NoOpPasswordEncoder.getInstance();
//    }
    
    @Bean
    @Primary
    public BCryptPasswordEncoder passwordEncoder2() {
    	return new BCryptPasswordEncoder();
    }
 
    @Bean
    public DaoAuthenticationProvider authenticationProvider2() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService2());
        authProvider.setPasswordEncoder(passwordEncoder2());
 
        return authProvider;
    }
 
    @Bean
    public SecurityFilterChain filterChain2(HttpSecurity http) throws Exception {
        http.authenticationProvider(authenticationProvider2());
 
        http.antMatcher("/doctor/**").authorizeRequests().anyRequest().authenticated()
            .and()
            .formLogin()
                .loginPage("/doctor/login")
                .usernameParameter("email")
                .loginProcessingUrl("/doctor/login")
                .defaultSuccessUrl("/doctor/home")
                .permitAll()
            .and()
                .logout()
                    .logoutUrl("/doctor/logout")
                    .logoutSuccessUrl("/");
 
        return http.build();
    }
}
