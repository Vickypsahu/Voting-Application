package com.vicky.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class config {
	
	@Autowired
	private UserDetailsServiceImpl detailsServiceImpl;
	
	
	
	 @Bean
	  public DaoAuthenticationProvider authenticationProvider() {
	      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	      authProvider.setUserDetailsService(detailsServiceImpl);
	      authProvider.setPasswordEncoder(passwordEncoder());
	      return authProvider;
	  }
	 
	  @Bean
	  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
	    return authConfig.getAuthenticationManager();
	  }

	  @Bean
	  public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	  }
	  
	  @Bean
	    public AuthenticationSuccessHandler successHandler() {
	        return (request, response, authentication) -> {
	            if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
	                response.sendRedirect("/admin/result");
	            } else if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER"))) {
	                response.sendRedirect("/test/home");
	            }
	        };
	    }
	  
	  
	  
	  @Bean
	  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http.csrf(csrf -> csrf.disable())
	    .cors(cors ->cors.disable())
	        .authorizeHttpRequests(auth -> 
	          auth.requestMatchers("/api/**").permitAll()
	          .requestMatchers("/test/**").hasRole("USER")
	          .requestMatchers("/admin/**").hasRole("ADMIN")
	              //.anyRequest().authenticated()
	              
	        )
	        .formLogin(l->
	        l.loginPage("/api/login").permitAll()
	       // .defaultSuccessUrl("/test/home")
	        .successHandler(successHandler())
	        )
	        .logout(log->
	        log.logoutUrl("/logout")
	        .logoutSuccessUrl("/api/login")
	        .invalidateHttpSession(true));
	       
            
	     
	    return http.build();
	  }


}
