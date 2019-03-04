package com.mum.groupproject.survey.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Choice implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	@Id
	private String uuid = UUID.randomUUID().toString();
	
	private String value;
	
	private String description;
	
	private Question question;
	
	
	private Timestamp recordedDate;
	
	private boolean deleted;
	
	public Choice(final Question question) {
		this.question = question;
		recordedDate = new Timestamp(System.currentTimeMillis());
		deleted = Boolean.FALSE;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
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
