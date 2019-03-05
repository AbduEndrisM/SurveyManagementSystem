package com.mum.groupproject.survey.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mum.groupproject.survey.dao.RateDao;
import com.mum.groupproject.survey.domain.Rate;
import com.mum.groupproject.survey.iservice.IRate;
import com.mum.groupproject.survey.utility.Messages;

@Service
@Transactional
public class RateService implements IRate {

	@Autowired
	private RateDao rateDao;

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

}
