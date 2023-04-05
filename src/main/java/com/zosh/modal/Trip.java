package com.zosh.modal;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
	public class Trip {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @ManyToOne
	    private User user;

	    @ManyToOne
	    private Driver driver;

	    @Temporal(TemporalType.TIMESTAMP)
	    private Date startTime;

	    @Temporal(TemporalType.TIMESTAMP)
	    private Date endTime;

	    private double startLat;

	    private double startLong;

	    private double endLat;

	    private double endLong;

	    private double distance;

	    private double fare;

	    // Constructors, getters, and setters
	}


}
