package com.book.library.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.book.library.security.CustomAccessDeniedHandler;
import com.book.library.security.JwtAuthenticationTokenFilter;
import com.book.library.security.RestAuthenticationEntryPoint;
import com.book.library.serviceImpl.UserServiceImpl;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UserServiceImpl userServiceImpl;
	@Autowired
	PasswordEncoder passwordEncoder;

	@Bean
	public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() throws Exception {
		JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter = new JwtAuthenticationTokenFilter();
		jwtAuthenticationTokenFilter.setAuthenticationManager(authenticationManager());
		return jwtAuthenticationTokenFilter;
	}

	@Bean
	public RestAuthenticationEntryPoint restServicesEntryPoint() {
		return new RestAuthenticationEntryPoint();
	}

	@Bean
	public CustomAccessDeniedHandler customAccessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}

	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
    protected void configure(HttpSecurity http) throws Exception {
        	http.csrf().disable()				
				.authorizeRequests()
                // No need authentication.
                .antMatchers("/apiv2/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/login").permitAll() 
                .antMatchers(HttpMethod.POST, "/api/register").permitAll()
                .antMatchers("/api/**").hasAnyRole("USER", "ADMIN")
                // Need authentication.
                .anyRequest().authenticated()
                .and().addFilterBefore(jwtAuthenticationTokenFilter(), 
                		UsernamePasswordAuthenticationFilter.class)
				.exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
	}
 
        @Override
    	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    		auth.userDetailsService(userServiceImpl).passwordEncoder(passwordEncoder);
    	}
    	
        @Bean
    	public PasswordEncoder passwordEncoder() {
    	     return new BCryptPasswordEncoder();
    	}
}
