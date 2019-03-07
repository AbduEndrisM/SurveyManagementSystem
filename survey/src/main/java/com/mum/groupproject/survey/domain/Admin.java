package com.mum.groupproject.survey.domain;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Admin extends User{

	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id = UUID.randomUUID().toString();
	
	
	private String firstName;
	
	
	private String lastName;


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	

}
