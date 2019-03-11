package com.mum.groupproject.survey;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mum.groupproject.survey.domain.Answer;
import com.mum.groupproject.survey.domain.Question;
import com.mum.groupproject.survey.serviceimpl.Answerservice;
import com.mum.groupproject.survey.utility.Messages;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SurveyApplicationTestsAnswer {
	@Test
	public void contextLoads() {
	}
	@Autowired
	Answerservice answerService;
	Answer answer = new Answer();

	@Test
	public void testCreateAnswer() {
		
		Question question = new Question();
		question.getId();
		question.setDescription("Election");
		answer.setQuestion(question);
		answer.setId("MER_123");
		answer.setValue("Answer Response");
		String newAcccountInserted = answerService.create(answer);
		assertEquals(newAcccountInserted,Messages.save);
	}
	
	@Test
	public void testUpdateAnswer() {
		answer.setValue("Sara");
		String newAcccountInserted = answerService.update(answer);
		assertEquals(newAcccountInserted, Messages.update);
	}
	
	@Test
	public void testDeleteSurvey() {
		answer.getQuestion();
		String newAcccountInserted = answerService.delete(answer);
		assertEquals(newAcccountInserted,Messages.delete);
	}

	

}
