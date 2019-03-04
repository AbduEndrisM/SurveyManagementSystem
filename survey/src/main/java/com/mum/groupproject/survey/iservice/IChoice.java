package com.mum.groupproject.survey.iservice;

import com.mum.groupproject.survey.domain.Choice;
import com.mum.groupproject.survey.domain.Question;

import java.util.*;

public interface IChoice {
	
	String create(final Choice choice);
	
	String update(final Choice choice);
	
	String delete(final Choice choice);
	
	
	List<Choice> questionChoices(final Question question);
	

}
