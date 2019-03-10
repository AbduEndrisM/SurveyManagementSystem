package com.mum.groupproject.survey.iservice;

import com.mum.groupproject.survey.domain.Question;
import com.mum.groupproject.survey.domain.QuestionType;
import com.mum.groupproject.survey.domain.Survey;

import java.util.*;

public interface IQuestion {
	
	String create(final Question question);
	
	String update(final Question quesiton);
	
	String delete(final Question quesiton);

	
	List<Question> surveyQuestion(final Survey survey);
	
	Question findOne(final String id);
	

	
	List<Question> questionByType(final String name,final Survey surveyß);

}
