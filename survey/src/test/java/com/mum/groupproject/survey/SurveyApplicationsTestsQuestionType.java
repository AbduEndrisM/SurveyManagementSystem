package com.mum.groupproject.survey;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mum.groupproject.survey.domain.Answer;
import com.mum.groupproject.survey.domain.Question;
import com.mum.groupproject.survey.domain.QuestionType;
import com.mum.groupproject.survey.serviceimpl.Answerservice;
import com.mum.groupproject.survey.serviceimpl.QuestionTypeService;
import com.mum.groupproject.survey.utility.Messages;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SurveyApplicationsTestsQuestionType {
	@Test
	public void contextLoads() {
	}
	@Autowired
	QuestionTypeService questionTypeService;
	QuestionType type = new QuestionType();

	@Test
	public void testCreateQuestionType() {
		type.setName("question type");
		type.setDescription("Mittern Project");
		String newAcccountInserted =questionTypeService.create(type);
		assertEquals(newAcccountInserted,Messages.save);
	}
	
	@Test
	public void testUpdateQuestionType() {
		type.setDescription("Presantation");
		String newAcccountInserted =questionTypeService.update(type);
		assertEquals(newAcccountInserted, Messages.update);
	}
	
	@Test
	public void testDeleteQuestionType() {
		type.getName();
		String newAcccountInserted = questionTypeService.delete(type);
		assertEquals(newAcccountInserted,"74101a42-110b-48b7-9b4c-c46f0752a987");
	}

	
	@Test
	public void testDeleteFindNameQuestionType() {
	
		QuestionType newAcccountInserted = questionTypeService.findByName( type.getName());
		assertEquals(newAcccountInserted,type.getName());
	}

	
}
