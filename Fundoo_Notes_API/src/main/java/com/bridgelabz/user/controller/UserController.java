package com.bridgelabz.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.response.Response;
import com.bridgelabz.user.dto.LoginDto;
import com.bridgelabz.user.dto.UserDto;
import com.bridgelabz.user.service.UserService;



@RestController
@RequestMapping("/user")
public class UserController
{
	@Autowired
	private UserService userService;
	
	@PostMapping("/regitration")
	public ResponseEntity<Response> Userregitration(@RequestBody UserDto userDto,HttpServletRequest request)
	   {
		StringBuffer requestUrl = request.getRequestURL();
	    Response response  = userService.registration(userDto ,requestUrl);
	    /* Response response = new Response(HttpStatus.OK.value(),message,null); */
	    return new ResponseEntity<Response>(response,HttpStatus.OK);
		
	   }
	
	@GetMapping("/loginUser")  
	public ResponseEntity<Response> login(@RequestBody LoginDto loginDto)
	{
     	Response response = userService.loginUser(loginDto);
	    return new ResponseEntity<Response>(response,HttpStatus.OK);
		
	}
	
	@PostMapping("/forgot")
	public ResponseEntity<Response> forgotPassword(@RequestBody String emailId ,StringBuffer requestUrl ,HttpServletRequest request)
	{
	    StringBuffer requestUrl1= request.getRequestURL();
    	Response response =userService.forgotPassword(emailId,requestUrl);		 
		return new ResponseEntity<Response>(response,HttpStatus.OK);
     }

   @PutMapping("/reset")
   public ResponseEntity<Response>reSetPassword(@RequestBody String tocken,String password){
	
     Response response = userService.reSetPassword(tocken, password);

     return new ResponseEntity<Response>(response ,HttpStatus.OK);
}

}
