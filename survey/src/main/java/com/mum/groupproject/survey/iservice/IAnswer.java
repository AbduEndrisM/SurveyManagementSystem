package com.mum.groupproject.survey.iservice;

import com.mum.groupproject.survey.domain.Answer;

import java.util.*;

public interface IAnswer {
	
	
	String create(final Answer answer);
	
	String update(final Answer answer);
	
	String delete(final Answer answer);
	
	List<Answer> allAnswer();
	
	
	

}
