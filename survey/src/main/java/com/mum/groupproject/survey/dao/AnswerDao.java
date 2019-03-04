package com.mum.groupproject.survey.dao;

import org.springframework.stereotype.Repository;

import com.mum.groupproject.survey.domain.Answer;
import com.mum.groupproject.survey.domain.Question;

import java.util.*;

@Repository
public class AnswerDao extends GenericDao<Answer>{
	
	
	public List<Answer> allAnswers(){
		List<Answer> list = new ArrayList<>();
		for(Answer answer:allObejcts()) {
			if(!answer.isDeleted()) {
				list.add(answer);
			}
		}
		return list;
	}
	
	

}
