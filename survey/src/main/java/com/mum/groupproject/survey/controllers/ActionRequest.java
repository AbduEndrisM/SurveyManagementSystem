package com.mum.groupproject.survey.controllers;

import java.text.SimpleDateFormat;
import java.util.Map;

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

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String landing() {
		return "welcome to survey system";
	}

	@RequestMapping(value = "formSubmission/{option}")
	public String create(@PathVariable("option") String option, Map<String, String> map) {
		String result = "";
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		try {
			if (option.equals("survey")) {
				Survey survey = new Survey();
				survey.setName(map.get("name"));
				survey.setDescription(map.get("description"));
				survey.setOpenDate(format.parse(map.get("openDate")));
				survey.setEndDate(format.parse(map.get("endDate")));
				result = surveyService.create(survey);
			} else if (option.equals("question")) {
				Survey survey = surveyService.findOne(map.get("surveyId"));
				QuestionType type = typeService.findOne(map.get("typeId"));
				Question q = new Question(survey, type);
				q.setName(map.get("name"));
				q.setDescription(map.get("description"));
				result = questionService.create(q);
			}else if(option.equals("choice")) {
				
			}
		} catch (Exception e) {
			result = Messages.exception;
		}
		return result;
	}

}
