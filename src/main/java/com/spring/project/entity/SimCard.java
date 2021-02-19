package com.spring.project.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class SimCard {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String simName;
	private int price;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date date = new Date();
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "simCard")	
	private User user;	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSimName() {
		return simName;
	}

	public void setSimName(String simName) {
		this.simName = simName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "SimCard [id=" + id + ", simName=" + simName + ", price=" + price + ", date=" + date + "]";
	}
	
	
	
}
