package com.bridgelabz.configuration;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
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
	
	
	
	@Bean(destroyMethod = "close")
	public RestHighLevelClient client() {
		RestHighLevelClient client = new RestHighLevelClient(
				RestClient.builder(new HttpHost("localhost", 9200, "http")));
		return client;

	}
	
	@Bean
	public ModelMapper getModelMapper(){
		return new ModelMapper();		
	}
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
    @Bean
    public Response getRespomse() {
    	return new Response();
    }
@Bean 
public EncryptUtil getEncryptUtil(){
	return new EncryptUtil();
   }

}