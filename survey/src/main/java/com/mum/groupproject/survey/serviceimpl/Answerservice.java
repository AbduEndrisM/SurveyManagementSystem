package com.mum.groupproject.survey.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mum.groupproject.survey.dao.AnswerDao;
import com.mum.groupproject.survey.dao.ChoiceDao;
import com.mum.groupproject.survey.dao.QuestionDao;
import com.mum.groupproject.survey.domain.Answer;
import com.mum.groupproject.survey.domain.Choice;
import com.mum.groupproject.survey.domain.Question;
import com.mum.groupproject.survey.domain.Survey;
import com.mum.groupproject.survey.iservice.IAnswer;
import com.mum.groupproject.survey.iservice.IQuestion;
import com.mum.groupproject.survey.utility.Messages;

@Service
@Transactional
public class Answerservice implements IAnswer {

	@Autowired
	private AnswerDao answerDao;

	@Autowired
	private ChoiceDao choiceDao;

	@Autowired
	private QuestionDao questionDao;

	@Override
	public String create(Answer answer) {
		try {
			answerDao.create(answer);
			return Messages.save;
		} catch (Exception e) {
			e.printStackTrace();
			return Messages.exception;
		}
	}

	@Override
	public String update(Answer answer) {
		try {
			answerDao.update(answer);
			return Messages.update;
		} catch (Exception e) {
			return Messages.exception;
		}
	}

	@Override
	public String delete(Answer answer) {
		try {
			answer.setDeleted(true);
			answerDao.update(answer);
			return Messages.delete;
		} catch (Exception e) {
			return Messages.exception;
		}
	}

	@Override
	public List<Answer> allAnswer() {
		return answerDao.allAnswers();
	}

	@Override
	public List<Answer> surveyAnswrs(Survey survey) {
		List<Answer> surveyAnswers = new ArrayList<>();
		for (Answer answer : allAnswer()) {
			if (answer.getSurvey().getId().equals(survey.getId())) {
				surveyAnswers.add(answer);
			}
		}
		return surveyAnswers;
	}

	@Override
	public Map<Choice, Integer> questionChoiceAnswers(Question question) {
		return answerDao.questionChoiceOccurence(question);
	}

	@Override
	public List<Answer> openEndedAnswers(Question q) {
		return answerDao.questionAnswers(q);
		
	}

}
