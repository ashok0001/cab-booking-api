package com.zosh.request;

public class SignupRequest {
	
	private String email;
	
	private String fullName;
	
	private String password;
	
	public SignupRequest() {
		// TODO Auto-generated constructor stub
	}

	public SignupRequest(String email, String fullName, String password) {
		super();
		this.email = email;
		this.fullName = fullName;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
