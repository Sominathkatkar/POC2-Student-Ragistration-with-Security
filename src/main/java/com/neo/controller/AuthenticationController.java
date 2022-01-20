package com.neo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neo.model.JwtRequest;
import com.neo.model.JwtResponse;
import com.neo.service.CustomUserDetailsService;
import com.neo.util.JwtUtil;



@RestController
@RequestMapping("/authenticate")
public class AuthenticationController {
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private CustomUserDetailsService userService;
	
	 @GetMapping("/")
	    public String welcome() {
	        return "welcome to StudentSpringSecurityApp";
	    }

	    @PostMapping("/")
	    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception
	    {
	    	try
	    	{
	            authenticationManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(
	                            jwtRequest.getUsername(),
	                            jwtRequest.getPassword()
	                    )
	            );
	        } 
	    	catch (BadCredentialsException e)
	    	{
	            throw new Exception("Invalid username or password", e);
	    	}
	    	final UserDetails userDetails = userService.loadUserByUsername(jwtRequest.getUsername());
	        final String token = jwtUtil.generateToken(userDetails);
	        return new JwtResponse(token);
	    }
}
