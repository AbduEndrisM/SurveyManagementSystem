package com.mum.groupproject.survey.iservice;

import com.mum.groupproject.survey.domain.Survey;

import java.util.*;

public interface ISurvey {
	
	String create(final Survey survey);
	
	String update(final Survey survey);
	
	String delete(final Survey survey);
	
	List<Survey> allSurveys();
	
	
	Survey findOne(final String id);
	

}
