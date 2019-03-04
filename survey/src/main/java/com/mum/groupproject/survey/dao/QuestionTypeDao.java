package com.mum.groupproject.survey.dao;

import org.springframework.stereotype.Repository;

import com.mum.groupproject.survey.domain.QuestionType;

import java.util.*;

@Repository
public class QuestionTypeDao extends GenericDao<QuestionType>{
	
	
	public List<QuestionType> allTypes(){
		List<QuestionType> list = new ArrayList<>();
		for(QuestionType type:allObejcts()) {
			if(!type.isDeleted()) {list.add(type);}
		}
		return list;
	}

}
