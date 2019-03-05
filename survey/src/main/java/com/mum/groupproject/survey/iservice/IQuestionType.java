package com.mum.groupproject.survey.iservice;


import com.mum.groupproject.survey.domain.QuestionType;
import java.util.*;

public interface IQuestionType {
	
	String create(final QuestionType type);
	
	String update(final QuestionType type);
	
	String delete(final QuestionType type);
	
	List<QuestionType> allTypes();
	
	QuestionType findOne(final String id);

}
