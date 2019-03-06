package com.mum.groupproject.survey.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mum.groupproject.survey.dao.SurveyDao;
import com.mum.groupproject.survey.domain.Survey;
import com.mum.groupproject.survey.iservice.ISurvey;
import com.mum.groupproject.survey.utility.Messages;

@Service
@Transactional
public class SurveyService implements ISurvey{
	
	@Autowired
	private SurveyDao surveyDao;

	@Override
	public String create(Survey survey) {
		try {
			surveyDao.create(survey);
			return Messages.save;
		} catch (Exception e) {
			e.printStackTrace();
			return e.getLocalizedMessage();
		}
	}

	@Override
	public String update(Survey survey) {
		try {
			surveyDao.update(survey);
			return Messages.delete;
		} catch (Exception e) {
			return Messages.exception;
		}
	}

	@Override
	public String delete(Survey survey) {
		try {
			survey.setDeleted(true);
			surveyDao.update(survey);
			return Messages.delete;
		} catch (Exception e) {
			return Messages.exception;
		}
	}

	@Override
	public List<Survey> allSurveys() {
		return surveyDao.allSurveys();
	}

	@Override
	public Survey findOne(String id) {
		return surveyDao.findOne(id);
	}

}
