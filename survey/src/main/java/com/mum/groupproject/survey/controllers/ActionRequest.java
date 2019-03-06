package com.mum.groupproject.survey.controllers;

import java.text.SimpleDateFormat;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mum.groupproject.survey.domain.Choice;
import com.mum.groupproject.survey.domain.Question;
import com.mum.groupproject.survey.domain.QuestionType;
import com.mum.groupproject.survey.domain.Survey;
import com.mum.groupproject.survey.iservice.IChoice;
import com.mum.groupproject.survey.iservice.IQuestion;
import com.mum.groupproject.survey.iservice.IQuestionType;
import com.mum.groupproject.survey.iservice.ISurvey;
import com.mum.groupproject.survey.utility.Messages;

@RestController
public class ActionRequest {

	@Autowired
	private ISurvey surveyService;

	@Autowired
	private IQuestionType typeService;

	@Autowired
	private IQuestion questionService;

	@Autowired
	private IChoice choiceService;

	private List<String> list = new ArrayList<>();

	@RequestMapping(value = "formSubmission/{option}", method = RequestMethod.POST)
	public String create(@RequestParam Map<String, String> map, @PathVariable("option") String option) {
		String result = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if (option.equals("survey")) {
				System.out.println(map.get("name"));
				System.out.println(map.get("description"));
				System.out.println(map.get("openDate"));
				Survey survey = new Survey();
				survey.setName(map.get("name"));
				survey.setDescription(map.get("description"));
				survey.setOpenDate(format.parse(map.get("openDate")));
				survey.setEndDate(format.parse(map.get("endDate")));
				result = surveyService.create(survey);
				System.out.println(result);
			} else if (option.equals("question")) {
				Survey survey = surveyService.findOne(map.get("surveyId"));
				QuestionType type = typeService.findOne(map.get("typeId"));
				Question q = new Question(survey, type);
				q.setName(map.get("name"));
				q.setDescription(map.get("description"));
				result = questionService.create(q);
			} else if (option.equals("choice")) {
				Question question = questionService.findOne(map.get("questionId"));
				Choice choice = new Choice(question);
				choice.setValue(map.get("value"));
				choice.setDescription(map.get("description"));
				return choiceService.create(choice);
			} else {
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = Messages.exception;
		}
		return result;
	}

}
