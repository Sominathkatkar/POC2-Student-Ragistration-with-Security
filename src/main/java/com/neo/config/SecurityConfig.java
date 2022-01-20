package com.neo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.neo.filter.JwtFilter;
import com.neo.service.CustomUserDetailsService;
import com.neo.service.StudentServiceI;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private CustomUserDetailsService userService;

    @Autowired
    private JwtFilter jwtFilter;
    
	/*
	 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
	 * Exception { PasswordEncoder encoder =
	 * PasswordEncoderFactories.createDelegatingPasswordEncoder(); auth
	 * .inMemoryAuthentication() .withUser("user")
	 * .password(encoder.encode("12345678")) .roles("USER") .and()
	 * .withUser("admin") .password(encoder.encode("admin@123")) .roles("USER",
	 * "ADMIN"); }
	 */
    
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().disable();
		http.csrf().disable();
		http.authorizeRequests()
		.antMatchers(HttpMethod.POST,"/authenticate").permitAll()
		.antMatchers(HttpMethod.POST,"/api/user/").permitAll()
        .antMatchers(HttpMethod.POST,"/api/student/").hasRole("USER")
        .antMatchers(HttpMethod.GET,"/api/student/").hasRole("ADMIN")
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and().httpBasic();
		
        http.addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	 @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		  auth.userDetailsService(userService);
	    }
	 @Bean
	    public PasswordEncoder passwordEncode(){
	        return NoOpPasswordEncoder.getInstance();
	    }
	    @Override
	    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }
	    
}