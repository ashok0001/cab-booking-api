package com.zosh.modal;

import com.zosh.ride.domain.UserRole;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

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
	
	private UserRole role;
	
	private String password;
	
	@OneToOne(mappedBy = "driver")
	private License license;
	
	@OneToOne(mappedBy = "driver")
	private Ride ride;
	
	@OneToOne(mappedBy="driver")
	private Vehicle vehicle;
	
	public Driver() {
		// TODO Auto-generated constructor stub
	}

	public Driver(Integer id, String name, String email, String mobile, double ratig, double latitude, double longitude,
			UserRole role, String password, License license, Ride ride, Vehicle vehicle) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.ratig = ratig;
		this.latitude = latitude;
		this.longitude = longitude;
		this.role = role;
		this.password = password;
		this.license = license;
		this.ride = ride;
		this.vehicle = vehicle;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public License getLicense() {
		return license;
	}

	public void setLicense(License license) {
		this.license = license;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Ride getRide() {
		return ride;
	}
	public void setRide(Ride ride) {
		this.ride = ride;
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
