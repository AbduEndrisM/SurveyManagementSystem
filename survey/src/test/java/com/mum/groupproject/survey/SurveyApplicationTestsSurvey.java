package com.mum.groupproject.survey;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mum.groupproject.survey.domain.Question;
import com.mum.groupproject.survey.domain.Survey;
import com.mum.groupproject.survey.iservice.ISurvey;
import com.mum.groupproject.survey.serviceimpl.QuestionService;
import com.mum.groupproject.survey.utility.Messages;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SurveyApplicationTestsSurvey {

	@Test
	public void contextLoads() {
	}

	
	@Autowired
	private ISurvey surveyService;

	@Test
	public void testCreateSurvey() {
		Survey newSurvey = new Survey();
		newSurvey.setName("Election");
		String newAcccountInserted = surveyService.create(newSurvey);
		assertEquals(newAcccountInserted, Messages.save);
	}
	
	@Test
	public void testUpdateSurvey() {
		Survey newSurvey = new Survey();
		newSurvey.setName("Evaluation");
		String newAcccountInserted = surveyService.update(newSurvey);
		assertEquals(newAcccountInserted, Messages.update);
	}
	@Test
	public void testDeleteSurvey() {
		Survey newSurvey = new Survey();
		newSurvey.getName();
		String newAcccountInserted = surveyService.delete(newSurvey);
		assertEquals(newAcccountInserted, Messages.delete);
	}
	@Test
	public void testFindSurvey() {
		Survey newSurvey = new Survey();
		String id = newSurvey.getId();
		Survey newAcccountInserted = surveyService.findOne(id);
		assertEquals(newAcccountInserted, "id_database");
	}
	
	@Test
	public void testCreateAnswer() {
		Survey newSurvey = new Survey();
		newSurvey.setName("Election");
		String newAcccountInserted = surveyService.create(newSurvey);
		assertEquals(newAcccountInserted, Messages.save);
	}
	
	


}
