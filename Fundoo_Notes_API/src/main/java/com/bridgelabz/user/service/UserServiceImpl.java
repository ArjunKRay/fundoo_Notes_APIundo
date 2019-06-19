package com.bridgelabz.user.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.exception.UserException;
import com.bridgelabz.response.Response;
import com.bridgelabz.user.dto.LoginDto;
import com.bridgelabz.user.dto.UserDto;
import com.bridgelabz.user.model.Email;
import com.bridgelabz.user.model.User;
import com.bridgelabz.user.repository.UserRepository;
import com.bridgelabz.utility.EncryptUtil;
import com.bridgelabz.utility.ITockenGenerator;
import com.bridgelabz.utility.MailUtil;






@Service
public class UserServiceImpl implements UserService {
	@Autowired(required =true)
	ITockenGenerator tockenGenerator;
	
	@Autowired(required =true)
	private UserRepository userRepository;

	@Autowired(required =true)
	private MailUtil mailsender;

	@Autowired(required =true)
	private ModelMapper modelMapper;
	
	@Autowired(required =true)
	PasswordEncoder encoder;
	
	@Autowired
	EncryptUtil encryptUtil;
	
	@Override
	public Response registration(UserDto userDto,StringBuffer requestUrl)
	{
		Optional<User>optinalUser= userRepository.findByEmailId(userDto.getEmailId());
		  if(optinalUser.isPresent()) 
	       {	
		     User user = modelMapper.map(userDto,User.class)	;
	         user.setPassword(encoder.encode(userDto.getPassword()));
		     User saveUser = userRepository.save(user);
       try {
		     String tocken = tockenGenerator.generateTocken(saveUser.getUserId());
		     String activationUrl = requestUrl.substring(0, requestUrl.lastIndexOf("/")) +"/verification/"+tocken;
			
			 Email email = new Email();
			 email.setTo("kuamr.arjun6515@gmail.com");
			 email.setSubject("Account Verification");
			 email.setBody("Please verify EmailId using belowl ink \n"+activationUrl);
		     mailsender.send(email);
		     return new Response(200,"Email send successfuly",null); 
            }
      catch (Exception e)
              {
                e.printStackTrace();
                throw new UserException("fill carefully data");
              }
            }
    else {
			 throw new UserException ("EmailId already exist");
		  }
     }
 	  
	@Override
	public Response loginUser(LoginDto loginDto)
	 {
		Optional<User>user1 = userRepository.findByEmailId(loginDto.getEmailId());
	      
		    if(user1.isPresent())
		      {    
			    User user = user1.get();
			    String userId1 =user.getUserId() ;
	           try {
				       tockenGenerator.generateTocken(userId1);
			           
				       if(encryptUtil.isPassword(loginDto,user))
			            { 
			    	    return new Response(1,"login successfully",null);
			             }
			           else 
			            {
			        	throw new UserException(" Wrong Password") ;
			            }
	                  }
			         catch(Exception e)
	                 {
				        e.printStackTrace();
				        throw new UserException("somthing not matches"); 
			         }
		           
		        }
		      else
		       {
		       throw new UserException("");
		      }
	}
	
	@Override
	public Response forgotPassword(String emailId,StringBuffer requestUrl) {
		Optional<User> userOptional = userRepository.findByEmailId(emailId);
		if (userOptional.isPresent()) {
			User userSaved = userOptional.get();
			String id = userSaved.getUserId();
			
			try
			  {
				String tocken = tockenGenerator.generateTocken(id);
				String activationUrl =  requestUrl.substring(0, requestUrl.lastIndexOf("/")) + "/resetpassword" + tocken;
				Email email = new Email();
				email.setTo("kumar.arjun6515@gmail.com");
				email.setSubject("resetPassword");
				email.setBody("reset your password \n" + activationUrl);
				mailsender.send(email);
				return new Response(200, "Email Sent", null);
			   }
			catch (Exception e)
			   {
              	e.printStackTrace();
				throw new UserException("internal server error");
			   }
	       	} 
		   else 
		   {
			throw new UserException("User not present..");
		   }	
	
	 }

	@Override
	public Response reSetPassword(String tocken,String password) {
		
		  String id = tockenGenerator.verifyTocken(tocken);
		  Optional<User>userOptional = userRepository.findByUserId(id);
		
		      if(userOptional.isPresent())
		        {
			     User user = userOptional.get();
		         user.setPassword(encoder.encode(password));
		         userRepository.save(user);
		         return new Response(200,"Resetpassword successfully",null);
		        }
		      else 
		       {
		    	  throw new UserException("please enter vailid data") ;
		        }
		    
	   }
		
   public Response emailValidation(String tocken,User user ) {
		  String id =tockenGenerator.verifyTocken(tocken);
		  boolean present = userRepository.findByUserId(user.getUserId()).isPresent();
		       if(present)
		       {
		      	System.out.println("varified EmailId"); 
		    	user.setIsVerify("true");
		    	userRepository.save(user);
		    	new Response(200,"Data save successfully in database",null);
		        }
		     else
		       {
		    	throw new UserException("Emailid not varified");   
		       }
		       
		return null;
   }
     
}		 
