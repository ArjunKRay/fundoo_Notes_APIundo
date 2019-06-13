package com.bridgelabz.dto;

public class UserDto {
	
	
//	private String id;

	private String name ;
	private String emailId;
	private String mobileNo;
	private String password;
	
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserDto(String password, String emailId, String name, String mobileNo) {
		super();
		//this.id = id;
		this.name = name;
		this.emailId = emailId;
		this.mobileNo = mobileNo;
		this.password=password;
	}
	
	public UserDto() {
		
		}
	

	
	
	public  String getEmailId() {
		return emailId;
	}
	
	
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	
	public String getName() {
		return name;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getMobileNo() {
		return mobileNo;
	}
	
	
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	@Override
	public String toString() {
		return "UserDto [emailId=" + emailId + ", name=" + name + ", mobileNo=" + mobileNo + ", password=" + password
				+ "]";
	}
	
	
	

}
