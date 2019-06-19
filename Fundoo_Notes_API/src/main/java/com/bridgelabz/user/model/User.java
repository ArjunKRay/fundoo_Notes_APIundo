package com.bridgelabz.user.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bridgelabz.level.model.Level;
import com.bridgelabz.notes.model.Note;


 @Document(collection="user")
public class User {

	 
	@Id
	private String userId;
	
	@Indexed(unique=true)
	private String emailId;
	private String name;
	private String password;
	private String mobileNo;
	private String Verify;
	
	@DBRef
	private List<Note> notes;
	
	@DBRef
	private List<Level>listLevel;
	
	public User() {}
	
    public User(String userId, String emailId, String name, String password, String mobileNo, String Verify) { 
		
    	this.userId   = userId;
		this.emailId  = emailId;
		this.name     = name;
		this.password = password;
		this.mobileNo = mobileNo;
		this.Verify   = Verify; 
    }

    
   public void setIsVerify(String Verify)
   {
		this.Verify = Verify;
	       }
	  
   public String getIsVerify() 
   {
			return Verify;
	  }

   public void setEmailId(String emailId) 
   {
		this.emailId = emailId;
	}
	
	public String getEmailId()
	{
		return emailId;
	}



	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName() 
	{
		return name;
	}
	
	

	public void setPassword(String password) 
	{
		this.password = password;
	}

	public String getPassword()
	{
		return password;
	}
	
	public String getUserId() 
	{
		 return userId;
	 }

	public void setMobileNo(String mobileNo)

	{
		this.mobileNo = mobileNo;
	}
	
	public String getMobileNo() {

		return mobileNo;
	}
	
	@Override
	public String toString() 
	{
	  return "User [id=" + userId + ", emailId=" + emailId + ", name=" + name + ", password=" + password + ", mobileNo="
				+ mobileNo + ", isVerify=" + Verify + "]";
	}

	


}
