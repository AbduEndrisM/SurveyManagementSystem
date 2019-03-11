package com.mum.groupproject.survey;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mum.groupproject.survey.domain.Question;
import com.mum.groupproject.survey.serviceimpl.QuestionService;
import com.mum.groupproject.survey.utility.Messages;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SurveyApplicationTestsQuestion {
	@Test
	public void contextLoads() {
	}
	@Autowired
	QuestionService questionService;

	@Test
	public void testCreateQuiestion() {
		Question newQuestion = new Question();
		newQuestion.setDescription("Evaluation test");
		newQuestion.setName("Medor");
		String newAcccountInserted = questionService.create(newQuestion);
		assertEquals(newAcccountInserted,Messages.save);
	}
	
	@Test
	public void testUpdateQuiestion() {
		Question newQuestion = new Question();
		newQuestion=questionService.findOne("67082a55-4deb-434a-aedb-c6e835bcdde5");
		String newAcccountInserted = questionService.update(newQuestion);
		assertEquals(newAcccountInserted, Messages.update);

	}
	
	@Test
	public void testDeleteQuiestion() {
		Question newQuestion = new Question();
		newQuestion.getId();
		String newAcccountInserted = questionService.delete(newQuestion);
		assertEquals(newAcccountInserted,Messages.delete);
	}

	@Test
	public void testFindQuiestion() {
		Question newQuestion = new Question();
		Question newAcccountInserted = questionService.findOne(newQuestion.getId());
		assertEquals(newAcccountInserted,newQuestion.getName());
	}
	
}
