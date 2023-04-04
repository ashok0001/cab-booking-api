package com.zosh.modal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String fullName;
	private String email;
	private String mobile;
	private String password;
	private String profilePicture;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	

	public User(Integer id, String fullName, String email, String mobile, String password, String profilePicture) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.mobile = mobile;
		this.password = password;
		this.profilePicture = profilePicture;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", fullName=" + fullName + ", email=" + email + ", mobile=" + mobile + ", password="
				+ password + ", profilePicture=" + profilePicture + "]";
	}
	
	

}
