package com.zosh.modal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Driver {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String name;
	private String email;
	private String mobile;
	private double ratig;
	private double latitude;
	private double longitude;
	
	public Driver() {
		// TODO Auto-generated constructor stub
	}

	public Driver(Integer id, String name, String email, String mobile, double ratig, double latitude,
			double longitude) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.ratig = ratig;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public double getRatig() {
		return ratig;
	}

	public void setRatig(double ratig) {
		this.ratig = ratig;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "Driver [id=" + id + ", name=" + name + ", email=" + email + ", mobile=" + mobile + ", ratig=" + ratig
				+ ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}
	
	
}
