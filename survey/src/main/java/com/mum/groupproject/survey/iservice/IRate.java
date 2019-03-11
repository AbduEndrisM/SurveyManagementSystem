package com.mum.groupproject.survey.iservice;

import com.mum.groupproject.survey.domain.Answer;
import com.mum.groupproject.survey.domain.Choice;
import com.mum.groupproject.survey.domain.Question;
import com.mum.groupproject.survey.domain.Rate;
import com.mum.groupproject.survey.domain.Survey;

import java.util.*;

public interface IRate {
	
	
	String create(final Rate rate);
	
	String update(final Rate rate);
	
	String delete(final Rate rate);
	
	Map<Question,Integer> rateQuestions(final Survey survey);

}
