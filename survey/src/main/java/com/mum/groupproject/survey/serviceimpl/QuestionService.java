package com.mum.groupproject.survey.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mum.groupproject.survey.dao.QuestionDao;
import com.mum.groupproject.survey.domain.Question;
import com.mum.groupproject.survey.domain.Survey;
import com.mum.groupproject.survey.iservice.IQuestion;
import com.mum.groupproject.survey.utility.Messages;

@Service
@Transactional
public class QuestionService implements IQuestion {

	@Autowired
	private QuestionDao questionDao;

	@Override
	public String create(Question question) {
		try {
			questionDao.create(question);
			return Messages.save;
		} catch (Exception e) {
			return Messages.exception;
		}
	}

	@Override
	public String update(Question quesiton) {
		try {
			questionDao.update(quesiton);
			return Messages.update;
		} catch (Exception e) {
			return Messages.exception;
		}
	}

	@Override
	public String delete(Question quesiton) {
		try {
			quesiton.setDeleted(true);
			questionDao.update(quesiton);
			return Messages.delete;
		} catch (Exception e) {
			return Messages.exception;
		}
	}

	@Override
	public List<Question> surveyQuestion(Survey survey) {
		return questionDao.surveyQuestions(survey);
	}

	@Override
	public Question findOne(String id) {
		return questionDao.findOne(id);
	}

	@Override
	public List<Question> questionByType(String name, Survey survey) {
		List<Question> questions = surveyQuestion(survey);
		List<Question> list = new ArrayList<>();
		for (Question question : questions) {
			if (question.getQuestionType().getName().equalsIgnoreCase(name)) {
				list.add(question);
			}
		}
		return list;
	}

}
