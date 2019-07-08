package com.bridgelabz.user.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.response.Response;
import com.bridgelabz.user.dto.LoginDto;
import com.bridgelabz.user.dto.UserDto;
import com.bridgelabz.user.service.AmazonS3BucketServiceImpl;
import com.bridgelabz.user.service.UserService;

@RestController
@RequestMapping("/userservice")
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private AmazonS3BucketServiceImpl amazonClient;

	@PostMapping("/registration")
	public ResponseEntity<Response> userRegitration(@RequestBody UserDto userDto, HttpServletRequest request) {
		StringBuffer requestUrl = request.getRequestURL();
		Response response = userService.registration(userDto, requestUrl);
		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}

	@PostMapping("/login")
	public ResponseEntity<Response> loginUser(@RequestBody LoginDto loginDto, HttpServletResponse httpServletResponse) {
		String tocken = userService.loginUser(loginDto);
		httpServletResponse.setHeader("Authentication", tocken);
		Response response = new Response(HttpStatus.OK.value(), "User Logged...", null);
		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}

	@GetMapping("/forgotPassword")
	public ResponseEntity<Response> forgotPassword(@RequestHeader String emailId, HttpServletRequest request) {
		StringBuffer requestUrl = request.getRequestURL();
		Response response = userService.forgotPassword(emailId, requestUrl);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@PutMapping("/resetPassword")
	public ResponseEntity<Response> reSetPassword(@RequestHeader String tocken, @RequestParam String password) {
		Response response = userService.reSetPassword(tocken, password);
		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}

	@GetMapping("/verification/{tocken}")
	public ResponseEntity<Response> validation(@PathVariable String tocken) {
		String message = userService.userValidation(tocken);
		Response response = new Response(HttpStatus.OK.value(), message, null);
		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}

	@PostMapping("/uploadFile")
	public ResponseEntity<Response> uploadFile(@RequestPart(value = "multipartFile") MultipartFile multipartFile,
			@RequestHeader String token) throws IOException {
		Response response = amazonClient.uploadFile(multipartFile, token);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@DeleteMapping("/deleteFile")
	public ResponseEntity<Response> deleteFile(@RequestHeader String fileName, @RequestHeader String token) {
		Response response = amazonClient.deleteFileFromS3Bucket(fileName, token);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

}
