package com.mum.groupproject.survey.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.mum.groupproject.survey.dao.QuestionDao;
import com.mum.groupproject.survey.domain.Question;
import com.mum.groupproject.survey.domain.Survey;
import com.mum.groupproject.survey.iservice.IQuestion;
import com.mum.groupproject.survey.utility.Messages;


@Service
@Transactional
public class QuestionService implements IQuestion{

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
		// TODO Auto-generated method stub
		return null;
	}

}
