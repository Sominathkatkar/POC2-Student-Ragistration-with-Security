package com.neo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neo.entity.User;
import com.neo.service.CustomUserDetailsService;

@RestController
@RequestMapping("/api/user")

public class UserController {
	@Autowired
    private CustomUserDetailsService userService;
    
   // @Autowired
    //private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @PostMapping("/")
    public ResponseEntity<?> saveUser(@RequestBody User user)
    {
    	
    	//String password=user.getPassword();
    	//String encpass=bCryptPasswordEncoder.encode(password);
    	//user.setPassword(encpass);
    	return ResponseEntity.ok(userService.saveUser(user));
    }
}
