package com.mum.groupproject.survey.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class QuestionActivity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	private Question question;

	@ManyToOne
	private Survey survey;

	@ManyToOne
	private SurveyTaker taker;

	private Timestamp recordedDate;

	public QuestionActivity(final Question question, final Survey survey) {

		assert question != null : "Please, question can't be null";
		assert survey != null : "Please survey can't be null";

		this.question = question;
		this.survey = survey;
		this.recordedDate = new Timestamp(System.currentTimeMillis());
	}

	abstract String createActivity(final QuestionActivity activity);

	abstract String updateActivity(final QuestionActivity activity);

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	public Timestamp getRecordedDate() {
		return recordedDate;
	}

	public void setRecordedDate(Timestamp recordedDate) {
		this.recordedDate = recordedDate;
	}

}
