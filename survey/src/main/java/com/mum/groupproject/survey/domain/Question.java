package com.mum.groupproject.survey.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;

@Entity
public class Question implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	@Id
	private String id = UUID.randomUUID().toString();
	
	private String name;
	
	private String description;
	
	@ManyToOne
	private Survey survey;
	
	@ManyToOne
	private Question subQuestion;
	
	private QuestionType questionType;
	
	@Transient
	private  List<Question> subquestions;
	
	private Timestamp recordedDate;
	
	private boolean deleted;
	
	
	public Question(final Survey survey,final QuestionType type) {
		//checking if the survey and questionType are not null
		assert survey!= null : "Please, the question'survey can't be null";
		assert type != null : "Please, the question's questiontype null";
		
		//initializing value 
		this.survey = survey;
		this.questionType = type;
		this.subquestions = new ArrayList<>();
		this.recordedDate = new Timestamp(System.currentTimeMillis());
		this.deleted = Boolean.FALSE;
	}

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

	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	public Question getSubQuestion() {
		return subQuestion;
	}

	public void setSubQuestion(Question subQuestion) {
		this.subQuestion = subQuestion;
	}

	public List<Question> getSubquestions() {
		return subquestions;
	}

	public void setSubquestions(List<Question> subquestions) {
		this.subquestions = subquestions;
	}

	public QuestionType getQuestionType() {
		return questionType;
	}

	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
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
