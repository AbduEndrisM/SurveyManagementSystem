package com.mum.groupproject.survey.domain;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Answer extends QuestionActivity{
	
	
	private static final long serialVersionUID = 1L;

	@Id
	private String id = UUID.randomUUID().toString();
	
	private String value;
	
	
	public Answer(Question question, Survey survey, String value) {
		super(question, survey);
		this.value = value;
	}

	

	public String getValue() {
		return value;
	}





	public void setValue(String value) {
		this.value = value;
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
	
	
	

}
