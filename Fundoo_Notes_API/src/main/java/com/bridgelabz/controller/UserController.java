package com.bridgelabz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.dto.ForgotPasswordDto;
import com.bridgelabz.dto.LoginDto;
import com.bridgelabz.dto.UserDto;
import com.bridgelabz.model.User;
import com.bridgelabz.service.UserServiceImpl;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	
	
	@PostMapping("/regitration")
	public  User regitration(@RequestBody UserDto userDto)
	{
		return userServiceImpl.registration(userDto);
		
	}
	
	@RequestMapping("/loginUser")  // we can also used @GetMapping 
	public String login(@RequestBody LoginDto loginDto)
	{
		
	return userServiceImpl.loginUser(loginDto);
		
		
	}
	/*
	 * @PostMapping("/forgot") public User forgotPassword(@RequestBody LoginDto
	 * loginDto) {
	 * 
	 * return userServiceImpl.forgotPassword(loginDto); }
	 */	
	 
	
	 
	 
}


