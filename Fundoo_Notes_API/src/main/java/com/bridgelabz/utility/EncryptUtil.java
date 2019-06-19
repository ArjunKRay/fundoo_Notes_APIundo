package com.bridgelabz.utility;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.bridgelabz.user.dto.LoginDto;
import com.bridgelabz.user.model.User;




public class EncryptUtil 
{
	
      public String encryptPassword(String password) 
       {
	
	    return new BCryptPasswordEncoder().encode(password);
	
        }
      
      
      public boolean isPassword(LoginDto loginDto ,User user)
      {
    	  return new BCryptPasswordEncoder().matches(loginDto.getPassword(), user.getPassword());
    	 

      }
	
}
   