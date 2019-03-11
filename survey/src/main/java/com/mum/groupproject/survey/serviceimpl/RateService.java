package com.mum.groupproject.survey.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mum.groupproject.survey.dao.QuestionDao;
import com.mum.groupproject.survey.dao.RateDao;
import com.mum.groupproject.survey.domain.Question;
import com.mum.groupproject.survey.domain.Rate;
import com.mum.groupproject.survey.domain.Survey;
import com.mum.groupproject.survey.iservice.IRate;
import com.mum.groupproject.survey.utility.Messages;

import java.util.*;

@Service
@Transactional
public class RateService implements IRate {

	@Autowired
	private RateDao rateDao;
	
	@Autowired
	private QuestionDao questionDao;

	@Override
	public String create(Rate rate) {
		try {
			rateDao.create(rate);
			return Messages.save;
		} catch (Exception e) {
			return Messages.exception;
		}
	}

	@Override
	public String update(Rate rate) {
		try {
			rateDao.update(rate);
			return Messages.update;
		} catch (Exception e) {
			return Messages.exception;
		}
	}

	@Override
	public String delete(Rate rate) {
		try {
			rate.setDeleted(true);
			rateDao.update(rate);
			return Messages.delete;
		} catch (Exception e) {
			return Messages.exception;
		}
	}

	@Override
	public Map<Question, Integer> rateQuestions(Survey survey) {
		List<Question> questions = questionDao.surveyQuestions(survey);
		Map<Question, Integer> map = new HashMap<>();
		List<Rate> list = rateDao.allRates();
		for (Question question : questions) {
			int total = 0;
			for (Rate rate : list) {
				if (rate.getQuestion() != null && rate.getQuestion().getId().equals(question.getId())) {
					total += 1;
				}
			}
			map.put(question, total);
		}
		return map;
	}

}
