package com.zosh.response;

public class JwtResponce {
	
	private String jwt;
	private boolean isAuthenticated;
	
	public JwtResponce() {
		// TODO Auto-generated constructor stub
	}

	public JwtResponce(String jwt , boolean isAuthenticated) {
		super();
		this.jwt = jwt;
		this.isAuthenticated=isAuthenticated;
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public boolean isAuthenticated() {
		return isAuthenticated;
	}

	public void setAuthenticated(boolean isAuthenticated) {
		this.isAuthenticated = isAuthenticated;
	}
	
	

}
