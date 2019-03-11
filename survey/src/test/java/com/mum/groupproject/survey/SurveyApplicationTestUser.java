package com.mum.groupproject.survey;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mum.groupproject.survey.domain.Admin;
import com.mum.groupproject.survey.domain.User;
import com.mum.groupproject.survey.serviceimpl.UserService;
import com.mum.groupproject.survey.utility.Messages;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SurveyApplicationTestUser {
	@Test
	public void contextLoads() {
	}

	
	@Autowired
	private UserService surveyService;
	User user = new User();
	
	@Test
	public void testCreateUser() {
		user.setUsername("roro");
		user.setPassword("1234");
		String newAcccountInserted = surveyService.createAccount(user);
		assertEquals(newAcccountInserted, Messages.save);
	}
	
	@Test
	public void testUpdateUser() {
		user.setUsername("Medor");
		String newAcccountInserted = surveyService.updateAccount(user);
		assertEquals(newAcccountInserted, user.getUsername());
	}
	@Test
	public void testDeleteSurvey() {
		user.getUsername();
		String newAcccountInserted = surveyService.deleteAccount(user);
		assertEquals(newAcccountInserted, Messages.delete);
	}
	@Test
	public void testFindUser() {
		String use = user.getUsername(); 
		Admin ad = new Admin();
		Admin newAcccountInserted = surveyService.findAdminByusername(use);
		assertEquals(newAcccountInserted, ad.getUsername());
	}
	
	
	
}
