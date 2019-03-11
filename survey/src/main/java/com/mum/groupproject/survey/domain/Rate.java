package com.mum.groupproject.survey.domain;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Rate extends QuestionActivity{
	
	
	private static final long serialVersionUID = 1L;


	@Id
	private String id = UUID.randomUUID().toString();
	
	
	private int value;
	
	private boolean deleted;

	public Rate(Question question, Survey survey,int value) {
		super(question, survey);
		this.value = value;
		deleted = Boolean.FALSE;
	}
	
	

	public int getValue() {
		return value;
	}



	public void setValue(int value) {
		this.value = value;
	}
	
	



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}
	
	



	public boolean isDeleted() {
		return deleted;
	}



	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}



	@Override
	String createActivity(QuestionActivity activity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	String updateActivity(QuestionActivity activity) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Rate() {
		super(null, null);
	}

}
