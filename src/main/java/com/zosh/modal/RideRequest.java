package com.zosh.modal;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class RideRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@OneToOne(fetch = FetchType.LAZY)
	private User user;
	
	private String picupLocation;
	
	private String dropLocation;
	
	private String rideType;
	
	private String rideStatus;
	
	
	public RideRequest() {
		// TODO Auto-generated constructor stub
	}


	public RideRequest(Integer id, User user, String picupLocation, String dropLocation, String rideType,
			String rideStatus) {
		super();
		this.id = id;
		this.user = user;
		this.picupLocation = picupLocation;
		this.dropLocation = dropLocation;
		this.rideType = rideType;
		this.rideStatus = rideStatus;
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


	public String getPicupLocation() {
		return picupLocation;
	}


	public void setPicupLocation(String picupLocation) {
		this.picupLocation = picupLocation;
	}


	public String getDropLocation() {
		return dropLocation;
	}


	public void setDropLocation(String dropLocation) {
		this.dropLocation = dropLocation;
	}


	public String getRideType() {
		return rideType;
	}


	public void setRideType(String rideType) {
		this.rideType = rideType;
	}


	public String getRideStatus() {
		return rideStatus;
	}


	public void setRideStatus(String rideStatus) {
		this.rideStatus = rideStatus;
	}


	@Override
	public String toString() {
		return "RideRequest [id=" + id + ", user=" + user + ", picupLocation=" + picupLocation + ", dropLocation="
				+ dropLocation + ", rideType=" + rideType + ", rideStatus=" + rideStatus + "]";
	}
	
	
	
	

}
