package com.mum.groupproject.survey.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.mum.groupproject.survey.domain.Question;
import com.mum.groupproject.survey.domain.Survey;
import com.mum.groupproject.survey.iservice.IQuestion;
import com.mum.groupproject.survey.iservice.IQuestionType;
import com.mum.groupproject.survey.iservice.ISurvey;
import com.mum.groupproject.survey.serviceimpl.SurveyService;

import java.util.*;

@Controller
public class Access {

	@Autowired
	private ISurvey surveyService;

	@Autowired
	private IQuestion questionService;
	
	@Autowired
	private IQuestionType typeService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String landing(ModelMap model) {
		model.addAttribute("surveys", surveyService.allSurveys());
		return "back-End/Admin/home";
	}

	@RequestMapping(value = "survey/{option}/{sourceId}")
	public String pageNavigation(@PathVariable("option") String option, @PathVariable("sourceId") String sourceId,
			ModelMap model) {
		if (option.equals("questions")) {
			Survey survey = surveyService.findOne(sourceId);
			List<Question> questions = questionService.surveyQuestion(survey);
			model.addAttribute("types", typeService.allTypes());
			model.addAttribute("questions", questions);
			return "back-End/Admin/questions";
		} else {
			return "redirect:/";
		}
	}

}
