package com.mum.groupproject.survey.dao;

import org.springframework.stereotype.Repository;

import com.mum.groupproject.survey.domain.Question;
import com.mum.groupproject.survey.domain.Survey;

import java.util.*;

@Repository
public class QuestionDao extends GenericDao<Question> {

	public List<Question> allQuesitons() {
		List<Question> list = new ArrayList<>();
		for (Question q : allObejcts()) {
			if(!q.isDeleted()) {
			list.add(q);
			}
		}
		return list;
	}

	public List<Question> surveyQuestions(final Survey survey) {
		List<Question> list = new ArrayList<>();
		for (Question q : allQuesitons()) {
			if (q.getSurvey().equals(survey)) {
				list.add(q);
			}
		}
		return list;
	}

}
