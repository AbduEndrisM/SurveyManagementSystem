package com.mum.groupproject.survey.dao;

import org.springframework.stereotype.Repository;

import com.mum.groupproject.survey.domain.Survey;
import java.util.*;

@Repository
public class SurveyDao extends GenericDao<Survey>{
	
	public List<Survey> allSurveys() {
		List<Survey> list = new ArrayList<>();
		for (Survey survey : allObejcts()) {
			if (!survey.isDeleted()) {
				list.add(survey);
			}
		}
		return list;
	}
	
	

}
