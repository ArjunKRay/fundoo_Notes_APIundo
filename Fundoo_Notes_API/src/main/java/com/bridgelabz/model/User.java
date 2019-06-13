package com.bridgelabz.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


 @Document
public class User {

	 
	@Id                       
	private String userId;
	
	@Indexed(unique=true)
	private String emailId;
	private String name;
	private String password;
	private String mobileNo;
	private String isVerify;
	
	
	public User() {}
	
    public User(String userId, String emailId, String name, String password, String mobileNo, String isVerify) { 
		
    	this.userId   =  userId;
		this.emailId  = emailId;
		this.name     = name;
		this.password = password;
		this.mobileNo = mobileNo;
		this.isVerify = isVerify; 
    }

    
  public void setIsVerify(String isVerify) {
		this.isVerify = isVerify;
	       }
	  
   public String getIsVerify() {
			return isVerify;
	  }

   public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	public String getEmailId() {
		return emailId;
	}



	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}
	


	public void setMobileNo(String mobileNo)

	{
		this.mobileNo = mobileNo;
	}
	
	public String getMobileNo() {

		return mobileNo;
	}
	
	@Override
	public String toString() {
	  return "User [id=" + userId + ", emailId=" + emailId + ", name=" + name + ", password=" + password + ", mobileNo="
				+ mobileNo + ", isVerify=" + isVerify + "]";
	}


}
