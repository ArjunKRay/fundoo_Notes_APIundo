package com.bridgelabz.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.dto.ForgotPasswordDto;
import com.bridgelabz.dto.LoginDto;
import com.bridgelabz.dto.UserDto;
import com.bridgelabz.model.Email;
import com.bridgelabz.model.User;
import com.bridgelabz.repository.UserRepository;
import com.bridgelabz.utility.MailUtil;

@Service
public class UserServiceImpl implements UserService {

	@Autowired(required = true)
	private UserRepository userRepository;

	@Autowired(required = true)
	private MailUtil mailsender;

	@Autowired(required=true)
	private ModelMapper modelMapper;

	@Override
	public String loginUser(LoginDto loginDto) {

		boolean isEmailId = userRepository.findByEmailId(loginDto.getEmailId()).isPresent();
		boolean isPassword = loginDto.getPassword() != null;

		if (isEmailId && isPassword) {
			System.out.println("loginsuccessfull");
			return "WELCOME";
		}

		else {

			System.out.println("Invailid EmailId or Password ");
			return null;
		}

	}

	@Override
	public User registration(UserDto userDto) {
		
		Email email = new Email();

		boolean isEmailPresent = userRepository.findByEmailId(userDto.getEmailId()).isPresent();

		User user = modelMapper.map(userDto, User.class);

		if (isEmailPresent) {
			email.setTo("kuamr.arjun6515@gmail.com");
			email.setSubject("verify");
			email.setBody("http://localhost:8080/user");
			mailsender.send(email);
			
		}

		return userRepository.save(user);

	}

	@Override
	public User forgotPassword(LoginDto loginDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User reSetPassword(ForgotPasswordDto forgotPasswordDto) {
		// TODO Auto-generated method stub
		return null;
	}

}

/*
 * @Override public User forgotPassword(LoginDto loginDto) {
 * 
 * 
 * 
 * User user = modelMapper.map(loginDto,User.class); boolean isEmailId =
 * userRepository.findByEmailId(loginDto.getEmailId()).isPresent();
 * 
 * if(isEmailId) { email.setTo(loginDto.getEmailId());
 * 
 * email.setSubject("forgot");
 * 
 * email.setBody("link");
 * 
 * mailsender.send(email);
 * 
 * return user;
 * 
 * } else { System.out.println("Inavilid EmailId"); return null;
 * 
 * }
 * 
 * }
 */

/*
 * @Override public User reSetPassword(ForgotPasswordDto forgotPasswordDto) {
 * 
 * return null; }
 * 
 * }
 */
