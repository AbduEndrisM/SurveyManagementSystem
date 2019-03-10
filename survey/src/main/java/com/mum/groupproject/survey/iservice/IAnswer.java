package com.mum.groupproject.survey.iservice;

import com.mum.groupproject.survey.domain.Answer;
import com.mum.groupproject.survey.domain.Choice;
import com.mum.groupproject.survey.domain.Question;
import com.mum.groupproject.survey.domain.Survey;

import java.util.*;

public interface IAnswer {
	
	
	String create(final Answer answer);
	
	String update(final Answer answer);
	
	String delete(final Answer answer);
	
	List<Answer> allAnswer();
	
	List<Answer> surveyAnswrs(final Survey survey);
	
	Map<Choice,Integer> questionChoiceAnswers(final Question question);
	
	
	List<Answer> openEndedAnswers(final Question q);
	
	
	

}
