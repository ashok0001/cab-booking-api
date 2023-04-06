package com.zosh.modal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class License {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String licenseNumber;
	
	private String licenseState;
	
	private String licenseExpirationDate;
	
	@OneToOne
	private Driver driver;

	public License() {
		// TODO Auto-generated constructor stub
	}
	
	public License(String licenseNumber, String licenseState, String licenseExpirationDate) {
		super();
		this.licenseNumber = licenseNumber;
		this.licenseState = licenseState;
		this.licenseExpirationDate = licenseExpirationDate;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public String getLicenseState() {
		return licenseState;
	}

	public void setLicenseState(String licenseState) {
		this.licenseState = licenseState;
	}

	public String getLicenseExpirationDate() {
		return licenseExpirationDate;
	}

	public void setLicenseExpirationDate(String licenseExpirationDate) {
		this.licenseExpirationDate = licenseExpirationDate;
	}
	
	

}
