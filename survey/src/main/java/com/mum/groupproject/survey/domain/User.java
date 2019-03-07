package com.mum.groupproject.survey.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class User implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	private String username;
	
	private String password;
	
	private boolean active;
	
	private Timestamp recordedDate;
	
	private boolean deleted;
	

	
	
	public User() {
		this.active = Boolean.TRUE;
		this.recordedDate = new Timestamp(System.currentTimeMillis());
		this.deleted = Boolean.FALSE;
	}


	


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	public Timestamp getRecordedDate() {
		return recordedDate;
	}


	public void setRecordedDate(Timestamp recordedDate) {
		this.recordedDate = recordedDate;
	}


	public boolean isDeleted() {
		return deleted;
	}


	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	
	

}
