package com.mum.groupproject.survey.domain;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

import java.io.Serializable;
import java.util.*;

@Entity
public class Survey implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	@Id
	private String id = UUID.randomUUID().toString();
	
	private String name;
	
	private String description;
	
	private Date openDate;
	
	private Date endDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
	
	

}
