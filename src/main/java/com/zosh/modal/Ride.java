package com.zosh.modal;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Ride {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Driver driver; 
	
	@OneToOne(fetch = FetchType.LAZY)
	private RideRequest rideRequest;
	
	private double fare;
	
	public Ride() {
		// TODO Auto-generated constructor stub
	}

	public Ride(Integer id, User user, Driver driver, RideRequest rideRequest, double fare) {
		super();
		this.id = id;
		this.user = user;
		this.driver = driver;
		this.rideRequest = rideRequest;
		this.fare = fare;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public RideRequest getRideRequest() {
		return rideRequest;
	}

	public void setRideRequest(RideRequest rideRequest) {
		this.rideRequest = rideRequest;
	}

	public double getFare() {
		return fare;
	}

	public void setFare(double fare) {
		this.fare = fare;
	}

	@Override
	public String toString() {
		return "Ride [id=" + id + ", user=" + user + ", driver=" + driver + ", rideRequest=" + rideRequest + ", fare="
				+ fare + "]";
	}
	

}
