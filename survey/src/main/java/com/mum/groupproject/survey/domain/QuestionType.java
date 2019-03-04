package com.mum.groupproject.survey.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;

@Entity
public class QuestionType implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	@Id
	private String uuid = UUID.randomUUID().toString();
	
	private String name;
	
	private String description;
	
	@Transient
	private  List<Question> typeQuestions;
	
	private boolean deleted;
	
	private Timestamp recordedDate;
	
	
	
	
	public QuestionType() {
		typeQuestions = new ArrayList<>();
		deleted = Boolean.FALSE;
		recordedDate = new Timestamp(System.currentTimeMillis());
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
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

	public List<Question> getTypeQuestions() {
		return typeQuestions;
	}

	public void setTypeQuestions(List<Question> typeQuestions) {
		this.typeQuestions = typeQuestions;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Timestamp getRecordedDate() {
		return recordedDate;
	}

	public void setRecordedDate(Timestamp recordedDate) {
		this.recordedDate = recordedDate;
	}
	
	

}
