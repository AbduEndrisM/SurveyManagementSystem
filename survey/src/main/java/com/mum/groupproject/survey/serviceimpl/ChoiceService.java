package com.mum.groupproject.survey.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mum.groupproject.survey.dao.ChoiceDao;
import com.mum.groupproject.survey.domain.Choice;
import com.mum.groupproject.survey.domain.Question;
import com.mum.groupproject.survey.iservice.IChoice;
import com.mum.groupproject.survey.utility.Messages;

@Service
@Transactional
public class ChoiceService implements IChoice{
	
	@Autowired
	private ChoiceDao choiceDao;

	@Override
	public String create(Choice choice) {
		try {
			choiceDao.create(choice);
			return Messages.save;
		} catch (Exception e) {
			return Messages.exception;
		}
	}

	@Override
	public String update(Choice choice) {
		try {
			choiceDao.update(choice);
			return Messages.update;
		} catch (Exception e) {
			return Messages.exception;
		}
	}

	@Override
	public String delete(Choice choice) {
		try {
			choice.setDeleted(true);
			choiceDao.update(choice);
			return Messages.save;
		} catch (Exception e) {
			return Messages.exception;
		}
	}

	@Override
	public List<Choice> questionChoices(Question question) {
		return choiceDao.questionChoices(question);
	}

}
