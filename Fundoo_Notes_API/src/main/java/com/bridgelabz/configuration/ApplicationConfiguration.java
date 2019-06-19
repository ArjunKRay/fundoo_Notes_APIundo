package com.bridgelabz.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bridgelabz.response.Response;
import com.bridgelabz.utility.EncryptUtil;
import com.bridgelabz.utility.MailUtil;

@Configuration
@ComponentScan("com.bridgelabz")
public class ApplicationConfiguration {
	
	@Bean
	public ModelMapper getModelMapper()
	{
		return new ModelMapper();		
	}
	@Bean
	public PasswordEncoder getPasswordEncoder() 
	{
		return new BCryptPasswordEncoder();
	}
	
    public MailUtil getmailSender()
    {
 	
      return new MailUtil();
    }
    
    @Bean
    public Response getRespomse() 
    {
    	return new Response();
    }
@Bean 
public EncryptUtil getEncryptUtil()
{
	return new EncryptUtil();
}

}