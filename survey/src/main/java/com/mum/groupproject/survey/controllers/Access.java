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
import com.mum.groupproject.survey.iservice.IChoice;
import com.mum.groupproject.survey.iservice.IQuestion;
import com.mum.groupproject.survey.iservice.IQuestionType;
import com.mum.groupproject.survey.iservice.ISurvey;
import com.mum.groupproject.survey.utility.Messages;
import com.mum.groupproject.survey.utility.ToResuse;

import java.io.File;
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

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String landing(ModelMap model, HttpSession session) {
		session.removeAttribute("admin");
		session.removeAttribute("taker");
		session.removeAttribute("access");
		session.invalidate();
		return "login";
	}

	@RequestMapping(value = "/{option}", method = RequestMethod.GET)
	public String pageNavigation(@PathVariable("option") String option, ModelMap model) {
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
				if (q.getQuestionType().getName().equals("MCE")) {
					map.put(q, choiceService.questionChoices(q));
				} else {
					map.put(q, null);
				}
			}
			model.addAttribute("questions", map);
			return "back-End/client/surveyQuestions";
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

			if (name.endsWith(".csv") || name.endsWith(".xlsm") || name.endsWith(".xlx")) {
				// Get length of file in bytes
				long fileSizeInBytes = file.getSize();
				// Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
				double fileSizeInKB = fileSizeInBytes / 1024;
				// Convert the KB to MegaBytes (1 MB = 1024 KBytes)
				double fileSizeInMB = fileSizeInKB / 1024;

				if (fileSizeInMB <= 5) {

					XSSFWorkbook workbook = new XSSFWorkbook(originalFile);
					XSSFSheet sheet = workbook.getSheetAt(0);
					Row row;

					for (int i = 4; i <= sheet.getLastRowNum(); i++) {
						row = (Row) sheet.getRow(i);

						Question q = new Question();

						if (row.getCell(0) == null && row.getCell(1) == null && row.getCell(2) == null
								&& row.getCell(3) == null) {
							break;
						}

						// getting profile title
						if (row.getCell(0) == null) {

							q.setName("");
						} else {
							q.setName(row.getCell(0).toString());

						}
						QuestionType type = new QuestionType();
						// getting profile firstName
						if (row.getCell(1) == null) {

						} else {
							type = typeService.findByName(row.getCell(1).toString());
							if (type == null) {
								type.setName(row.getCell(1).toString());
								typeService.create(type);
								q.setQuestionType(type);
							}
						}
						List<Choice> list = new ArrayList<>();
						if (row.getCell(1).toString().equalsIgnoreCase("MC")) {
							Choice choice = new Choice();
							choice.setValue(row.getCell(2).toString());
							Choice choice1 = new Choice();
							choice1.setValue(row.getCell(3).toString());
							Choice choice2 = new Choice();
							choice2.setValue(row.getCell(4).toString());
							Choice choice3 = new Choice();
							choice3.setValue(row.getCell(5).toString());

							list.add(choice);
							list.add(choice1);
							list.add(choice2);
							list.add(choice3);
						}
						typeService.create(type);
						q.setQuestionType(type);
						questionService.create(q);
						if (!list.isEmpty()) {
							for (Choice c : list) {
								c.setQuestion(q);
								choiceService.create(c);
							}
						}
					}

					String message = Messages.save;
					redirectAttributes.addFlashAttribute("Success", message);
				} else {
					redirectAttributes.addFlashAttribute("Failure", "Sorry Excel Sheet Must Not Exceed ");
					System.out.println("Sorry Excel Sheet Must Not Exceed ");
				}
			} else {
				redirectAttributes.addFlashAttribute("Failure", "Sorry you are trying to upload Non-Excel Sheet");
				System.out.println("Sorry Excel Sheet Must Not Exceed ");
			}
		}
		return "redirect:/";
	}

}
