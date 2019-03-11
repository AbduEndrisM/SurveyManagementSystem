package com.mum.groupproject.survey.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mum.groupproject.survey.domain.Question;
import com.mum.groupproject.survey.domain.Survey;
import com.mum.groupproject.survey.iservice.IQuestion;
import com.mum.groupproject.survey.iservice.ISurvey;
import com.mum.groupproject.survey.utility.Messages;

import java.util.*;

@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	private ISurvey surveyService;

	@Autowired
	private IQuestion questionService;

	@RequestMapping(value = "/{option}", method = RequestMethod.GET)
	public String access(@PathVariable("option") String option, HttpSession session, ModelMap model) {
		String result = "";
		try {
			if (option.equals("surveys")) {
				List<Survey> list = surveyService.allSurveys();
				model.addAttribute("surveys", list);
				result = "back-End/client/home";
			} else if (option.contains("surveyQuestions")) {
				Survey survey = surveyService.findOne(option.split("_")[1]);
				List<Question> list = questionService.surveyQuestion(survey);
				model.addAttribute("survey", survey);
				model.addAttribute("questions", list);
				return "back-End/client/questions";
			} else if (option.equals("logout")) {
				session.removeAttribute("taker");
				session.removeAttribute("admin");
				result = "redirect:/";
			}
		} catch (Exception e) {
			result = Messages.exception;
		}
		return result;
	}

}
