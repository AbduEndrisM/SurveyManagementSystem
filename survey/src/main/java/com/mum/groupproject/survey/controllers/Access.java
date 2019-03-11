package com.mum.groupproject.survey.controllers;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mum.groupproject.survey.domain.Choice;
import com.mum.groupproject.survey.domain.Question;
import com.mum.groupproject.survey.domain.QuestionType;
import com.mum.groupproject.survey.domain.Survey;
import com.mum.groupproject.survey.domain.SurveyTaker;
import com.mum.groupproject.survey.iservice.IChoice;
import com.mum.groupproject.survey.iservice.IQuestion;
import com.mum.groupproject.survey.iservice.IQuestionType;
import com.mum.groupproject.survey.iservice.ISurvey;
import com.mum.groupproject.survey.utility.Messages;
import com.mum.groupproject.survey.utility.ToResuse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

import javax.servlet.http.HttpSession;

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

	String line = "";

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String landing(ModelMap model, HttpSession session) {
		session.removeAttribute("admin");
		session.removeAttribute("taker");
		session.removeAttribute("access");
		session.invalidate();
		return "login";
	}

	@RequestMapping(value = "/{option}", method = RequestMethod.GET)
	public String pageNavigation(@PathVariable("option") String option, ModelMap model,HttpSession session) {
		String sourceId = option.contains("_") ? option.split("_")[1] : "";

		if (option.equals("survey")) {
			List<Survey> list = surveyService.allSurveys();
			model.addAttribute("surveys", list);
			return "back-End/client/home";
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
			Map<Question, List<Choice>> map = new HashMap<>();
			model.addAttribute("survey", survey);
			for (Question q : list) {
				if (q.getQuestionType().getName().equals("MC")) {
					map.put(q, choiceService.questionChoices(q));
				} else {
					map.put(q, null);
				}
			}

			model.addAttribute("questions", map);
			return "back-End/client/surveyQuestions";
		} else if (option.equals("account")) {
			SurveyTaker taker = (SurveyTaker)session.getAttribute("taker");
			model.addAttribute("taker", taker);
			return "back-End/client/account";
		} else if (option.equals("resultPage")) {
			return "back-End/client/resultPage";
		} else {
			return "redirect:/";
		}
	}

	@SuppressWarnings("null")
	@RequestMapping(value = "/data/uploadExcel", method = RequestMethod.POST)
	public String upload(@RequestParam("attachment") MultipartFile file, RedirectAttributes redirectAttributes,
			@RequestParam Map<String, String> params, ModelMap model, HttpSession session) throws Exception {
		if (!file.isEmpty()) {

			File originalFile = ToResuse.convert(file);

			String name = originalFile.getName();

			Survey survey = surveyService.findOne(params.get("surveyId"));

			BufferedReader reader = new BufferedReader(new FileReader(originalFile));
			while ((line = reader.readLine()) != null) {
				String data[] = line.split(",");
				Question question = new Question();
				question.setName(data[0]);
				QuestionType type = typeService.findByName(data[1]);
				if (type == null) {
					type = new QuestionType();
					type.setName(data[1]);
					typeService.create(type);
				}
				List<Choice> list = new ArrayList<>();
				if (data[1].equalsIgnoreCase("MC")) {
					Choice c = new Choice();
					c.setValue(data[2]);
					Choice c1 = new Choice();
					c1.setValue(data[3]);
					Choice c2 = new Choice();
					c2.setValue(data[4]);
					Choice c3 = new Choice();
					c3.setValue(data[5]);
					
					if (data.length == 7) {
						if (Integer.parseInt(data[6]) == 1) {
							c.setChoosen(true);
						} else if (Integer.parseInt(data[6]) == 2) {
							c1.setChoosen(true);
						} else if (Integer.parseInt(data[6]) == 3) {
							c2.setChoosen(true);
						} else if (Integer.parseInt(data[6]) == 4) {
							c3.setChoosen(true);
						}
					}
					list.add(c);
					list.add(c1);
					list.add(c2);
					list.add(c3);
				}
				question.setSurvey(survey);
				question.setQuestionType(type);
				questionService.create(question);
				if (list.size() > 0) {
					for (Choice c : list) {
						c.setQuestion(question);
						choiceService.create(c);
					}
				}
			}

		}
		return "redirect:/";
	}

}
