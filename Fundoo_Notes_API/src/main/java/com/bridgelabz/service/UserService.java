package com.bridgelabz.service;

import com.bridgelabz.dto.ForgotPasswordDto;
import com.bridgelabz.dto.LoginDto;
import com.bridgelabz.dto.UserDto;
import com.bridgelabz.model.User;

public interface UserService {
	
	String loginUser(LoginDto loginDto);
	
	User registration(UserDto userDto);
	
    User forgotPassword(LoginDto loginDto);
    User reSetPassword(ForgotPasswordDto forgotPasswordDto);
	
	
	

}
