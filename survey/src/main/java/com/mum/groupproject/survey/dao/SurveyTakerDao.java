package com.mum.groupproject.survey.dao;

import org.springframework.stereotype.Repository;

import com.mum.groupproject.survey.domain.SurveyTaker;

import java.util.*;

@Repository
public class SurveyTakerDao extends GenericDao<SurveyTaker>{
	
	
	public List<SurveyTaker> takers(){
		List<SurveyTaker> list = new ArrayList<>();
		for(SurveyTaker taker:allObejcts()) {
			if(!taker.isDeleted()) {
				list.add(taker);
			}
		}
		return list;
	}
	
	public SurveyTaker findTakerByUsername(final String username) {
		for(SurveyTaker surveyTaker:allObejcts()) {
			if(surveyTaker.getUsername().equals(username)) {
				return surveyTaker;
			}
		}
		return null;
	}

}
