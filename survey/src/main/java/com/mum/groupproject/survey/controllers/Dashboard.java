package com.mum.groupproject.survey.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mum.groupproject.survey.domain.Choice;
import com.mum.groupproject.survey.domain.Question;
import com.mum.groupproject.survey.domain.Survey;
import com.mum.groupproject.survey.iservice.IAnswer;
import com.mum.groupproject.survey.iservice.IQuestion;
import com.mum.groupproject.survey.iservice.ISurvey;
import com.mum.groupproject.survey.utility.Messages;

@RestController
public class Dashboard {

	@Autowired
	private IQuestion questionService;

	@Autowired
	private IAnswer answerService;

	@Autowired
	private ISurvey surveyService;

	// handling request from front-end to return result in json format
	@RequestMapping(value = "admin/dashboardJson/{option}", method = RequestMethod.POST)
	public String getJsonData(@RequestParam Map<String, String> params, @PathVariable("option") String option,
			HttpSession session) {
		String data = "";
		try {

			Question question = questionService.findOne(option);
			System.out.println("Question name :" + question.getName());
			Map<Choice, Integer> map = answerService.questionChoiceAnswers(question);
			Map<String, Integer> mm = new HashMap<>();
			for (Map.Entry<Choice, Integer> m : map.entrySet()) {
				mm.put(m.getKey().getValue(), m.getValue());
			}

			ObjectMapper mapper = new ObjectMapper();
			data = mapper.writeValueAsString(mm);

		} catch (Exception e) {
			e.printStackTrace();
			data = Messages.exception;
		}
		return data;
	}

	@RequestMapping(value = "admin/allQuestions/{option}", method = RequestMethod.POST)
	public String getRequestData(@RequestParam Map<String, String> params, @PathVariable("option") String option,
			HttpSession session) {
		String data = "";
		try {
			String surveyId = (String) session.getAttribute("surveyId");
			Survey survey = surveyService.findOne(surveyId);
			List<Question> list = questionService.surveyQuestion(survey);
			Map<String, Map<String, Integer>> superMap = new HashMap<>();
			for (Question question : list) {

				Map<Choice, Integer> map = answerService.questionChoiceAnswers(question);
				Map<String, Integer> mm = new HashMap<>();
				for (Map.Entry<Choice, Integer> m : map.entrySet()) {
					mm.put(m.getKey().getValue(), m.getValue());
				}
				superMap.put(question.getName(), mm);
			}

			ObjectMapper mapper = new ObjectMapper();
			data = mapper.writeValueAsString(superMap);

		} catch (Exception e) {
			e.printStackTrace();
			data = Messages.exception;
		}
		return data;
	}

}
