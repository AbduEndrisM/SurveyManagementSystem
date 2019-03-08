package com.mum.groupproject.survey.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mum.groupproject.survey.dao.AnswerDao;
import com.mum.groupproject.survey.domain.Answer;
import com.mum.groupproject.survey.iservice.IAnswer;
import com.mum.groupproject.survey.utility.Messages;


@Service
@Transactional
public class Answerservice implements IAnswer{
	
	@Autowired
	private AnswerDao answerDao;

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

	

}
