package com.mum.groupproject.survey.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.mum.groupproject.survey.dao.QuestionTypeDao;
import com.mum.groupproject.survey.domain.QuestionType;
import com.mum.groupproject.survey.iservice.IQuestionType;
import com.mum.groupproject.survey.utility.Messages;

@Service
@Transactional
public class QuestionTypeService implements IQuestionType{
	
	
	private QuestionTypeDao typeDao;

	@Override
	public String create(QuestionType type) {
		try {
			typeDao.create(type);
			return Messages.save;
		} catch (Exception e) {
			return Messages.exception;
		}
	}

	@Override
	public String update(QuestionType type) {
		try {
			typeDao.update(type);
			return Messages.update;
		} catch (Exception e) {
			return Messages.exception;
		}
	}

	@Override
	public String delete(QuestionType type) {
		try {
			type.setDeleted(true);
			typeDao.update(type);
			return Messages.delete;
		} catch (Exception e) {
			return Messages.exception;
		}
	}

	@Override
	public List<QuestionType> allTypes() {
		return typeDao.allTypes();
	}

	@Override
	public QuestionType findOne(String id) {
		return typeDao.findOne(id);
	}

}
