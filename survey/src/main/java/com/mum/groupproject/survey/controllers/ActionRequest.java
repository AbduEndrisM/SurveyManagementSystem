package com.mum.groupproject.survey.controllers;

import java.text.SimpleDateFormat;

import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mum.groupproject.survey.domain.Admin;
import com.mum.groupproject.survey.domain.Answer;
import com.mum.groupproject.survey.domain.Choice;
import com.mum.groupproject.survey.domain.Question;
import com.mum.groupproject.survey.domain.QuestionActivity;
import com.mum.groupproject.survey.domain.QuestionType;
import com.mum.groupproject.survey.domain.Rate;
import com.mum.groupproject.survey.domain.Survey;
import com.mum.groupproject.survey.domain.SurveyTaker;
import com.mum.groupproject.survey.domain.User;
import com.mum.groupproject.survey.iservice.IAnswer;
import com.mum.groupproject.survey.iservice.IChoice;
import com.mum.groupproject.survey.iservice.IQuestion;
import com.mum.groupproject.survey.iservice.IQuestionActivityService;
import com.mum.groupproject.survey.iservice.IQuestionType;
import com.mum.groupproject.survey.iservice.ISurvey;
import com.mum.groupproject.survey.iservice.IuserService;
import com.mum.groupproject.survey.utility.Md5;
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

	@Autowired
	private IuserService userService;

	@Autowired
	private IAnswer answerService;

	@Autowired
	private IQuestionActivityService questionActivityService;

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
				System.out.println("typeId :" + map.get("typeId"));

				QuestionType type = typeService.findOne(map.get("typeId"));
				System.out.println("details :" + type.getName());
				Question q = new Question(survey, type);
				q.setName(map.get("name"));
				q.setDescription(map.get("description"));
				result = questionService.create(q);
			} else if (option.equals("choice")) {
				System.out.println("questionId :" + map.get("questionId"));
				Question question = questionService.findOne(map.get("questionId"));
				Choice choice = new Choice(question);
				choice.setValue(map.get("value"));
				choice.setDescription(map.get("description"));
				return choiceService.create(choice);
			} else if (option.equals("submitAnswers")) {
				List<QuestionActivity> activities = new ArrayList<>();
				Survey survey = surveyService.findOne(map.get("surveyId"));
				List<Question> surveyQuestions = questionService.surveyQuestion(survey);
				for (Question question : surveyQuestions) {
					QuestionActivity answer = new Answer(question, survey, map.get("answer_" + question.getId()));
					QuestionActivity rate = new Rate(question, survey,
							Integer.parseInt(map.get("rate_" + question.getId())));
					activities.add(answer);
					activities.add(rate);

				}
				return questionActivityService.createQuestionActivity(activities);
			} else if (option.contains("openQuestions")) {
				Question q = questionService.findOne(option.split("_")[1]);
				List<Answer> answers = answerService.openEndedAnswers(q);
				ObjectMapper mapper = new ObjectMapper();
				String value = mapper.writeValueAsString(answers);
				return value;

			} else {
				return "UNKOWN REQUEST";
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = Messages.exception;
		}
		return result;
	}

	@RequestMapping(value = "login/{option}", method = RequestMethod.POST)
	public String login(@RequestParam Map<String, String> map, @PathVariable("option") String option,
			HttpSession session) {
		String result = "";
		try {
			if (option.equals("surveyTaker")) {
				SurveyTaker survey = userService.findTakerByuername(map.get("username"));
				if (survey != null) {
					if (survey.getPassword().equals(Md5.md5(map.get("password")))) {
						session.setAttribute("taker", survey);
						session.setAttribute("access", "taker");
						result = Messages.access;
					} else {
						result = Messages.unknown;
					}
				} else {
					result = Messages.unknown;
				}
			} else if (option.equals("admin")) {
				Admin admin = userService.findAdminByusername(map.get("username"));
				if (admin != null) {
					if (admin.getPassword().equals(Md5.md5(map.get("password")))) {
						session.setAttribute("admin", admin);
						session.setAttribute("access", "admin");
						result = Messages.access;
					} else {
						result = Messages.unknown;
					}
				} else if (map.get("username").equals("admin") && map.get("password").equals("admin")) {
					result = Messages.access;
				} else {
					result = Messages.unknown;
				}
			} else {
				return Messages.exception;
			}

		} catch (Exception e) {
			result = Messages.exception;
		}
		return result;
	}

	@RequestMapping(value = "register/{option}")
	public String register(@RequestParam Map<String, String> map, @PathVariable("option") String option) {
		String result = "";
		try {
			if (option.equals("taker")) {
				User user = new SurveyTaker();
				user.setUsername(map.get("username"));
				user.setPassword(map.get("password"));
				if (user.getPassword().equals(map.get("confirm"))) {
					result = userService.createAccount(user);
				} else {
					result = Messages.mistmatch;
				}
			}
		} catch (Exception e) {
			result = Messages.exception;
		}
		return result;
	}

}
