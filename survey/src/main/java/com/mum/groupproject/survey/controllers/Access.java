package com.mum.groupproject.survey.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.mum.groupproject.survey.domain.Choice;
import com.mum.groupproject.survey.domain.Question;
import com.mum.groupproject.survey.domain.QuestionType;
import com.mum.groupproject.survey.domain.Survey;
import com.mum.groupproject.survey.iservice.IChoice;
import com.mum.groupproject.survey.iservice.IQuestion;
import com.mum.groupproject.survey.iservice.IQuestionType;
import com.mum.groupproject.survey.iservice.ISurvey;

import java.util.*;

@Controller
public class Access {

	@Autowired
	private ISurvey surveyService;

	@Autowired
	private IQuestion questionService;

	@Autowired
	private IQuestionType typeService;

	@Autowired
	private IChoice choiceService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String landing(ModelMap model) {

		return "login";
	}

	@RequestMapping(value = "/{option}", method = RequestMethod.GET)
	public String pageNavigation(@PathVariable("option") String option, ModelMap model) {
		String sourceId = option.contains("_") ? option.split("_")[1] : "";

		if (option.equals("survey")) {
			List<Survey> list = surveyService.allSurveys();
			model.addAttribute("surveys", list);
			return "back-End/Admin/home";
		} else if (option.contains("questions")) {
			Survey survey = surveyService.findOne(sourceId);
			List<Question> questions = questionService.surveyQuestion(survey);
			model.addAttribute("types", typeService.allTypes());
			model.addAttribute("surveyId", sourceId);
			model.addAttribute("questions", questions);
			return "back-End/Admin/questions";
		} else if (option.contains("choice")) {
			Question question = questionService.findOne(sourceId);
			List<Choice> list = choiceService.questionChoices(question);
			model.addAttribute("choices", list);
			model.addAttribute("question", question);
			return "back-End/Admin/choices";
		} else if (option.equals("questionTypes")) {
			List<QuestionType> types = typeService.allTypes();
			model.addAttribute("types", types);
			return "back-End/Admin/questionTypes";
		} else if (option.equals("home")) {
			List<Survey> list = surveyService.allSurveys();
			model.addAttribute("surveys", list);
			return "back-End/client/home";
		} else if (option.contains("surveyDetails")) {
			Survey survey = surveyService.findOne(sourceId);
			List<Question> list = questionService.surveyQuestion(survey);
			model.addAttribute("survey", survey);
			model.addAttribute("questions", list);
			return "back-End/client/surveyQuestions";
		} else {
			return "redirect:/";
		}
	}

}
