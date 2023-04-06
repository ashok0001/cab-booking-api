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
	
	private double pickupLatitude;
	private double pickupLongitude;
	
	private double destinationLatitude;
	private double destinationLongitude;
	
	private boolean isStarted;
	private boolean isCompleted;
	
	private double fare;
	
	public Ride() {
		// TODO Auto-generated constructor stub
	}

	public Ride(Integer id, User user, Driver driver, double pickupLatitude, double pickupLongitude,
			double destinationLatitude, double destinationLongitude, boolean isStarted, boolean isCompleted,
			double fare) {
		super();
		this.id = id;
		this.user = user;
		this.driver = driver;
		this.pickupLatitude = pickupLatitude;
		this.pickupLongitude = pickupLongitude;
		this.destinationLatitude = destinationLatitude;
		this.destinationLongitude = destinationLongitude;
		this.isStarted = isStarted;
		this.isCompleted = isCompleted;
		this.fare = fare;
	}

	public double getPickupLatitude() {
		return pickupLatitude;
	}

	public void setPickupLatitude(double pickupLatitude) {
		this.pickupLatitude = pickupLatitude;
	}





	public double getPickupLongitude() {
		return pickupLongitude;
	}





	public void setPickupLongitude(double pickupLongitude) {
		this.pickupLongitude = pickupLongitude;
	}





	public double getDestinationLatitude() {
		return destinationLatitude;
	}





	public void setDestinationLatitude(double destinationLatitude) {
		this.destinationLatitude = destinationLatitude;
	}





	public double getDestinationLongitude() {
		return destinationLongitude;
	}





	public void setDestinationLongitude(double destinationLongitude) {
		this.destinationLongitude = destinationLongitude;
	}





	public boolean isStarted() {
		return isStarted;
	}

	public void setStarted(boolean isStarted) {
		this.isStarted = isStarted;
	}

	public boolean isCompleted() {
		return isCompleted;
	}



	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
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



	public double getFare() {
		return fare;
	}

	public void setFare(double fare) {
		this.fare = fare;
	}



}
