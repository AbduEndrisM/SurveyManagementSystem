package com.mum.groupproject.survey.domain;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SurveyTaker extends User{

	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id = UUID.randomUUID().toString();
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	

}
