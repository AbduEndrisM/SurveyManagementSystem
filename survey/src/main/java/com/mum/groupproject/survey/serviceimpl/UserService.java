package com.mum.groupproject.survey.serviceimpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mum.groupproject.survey.dao.AdminDao;
import com.mum.groupproject.survey.dao.SurveyTakerDao;
import com.mum.groupproject.survey.domain.Admin;
import com.mum.groupproject.survey.domain.SurveyTaker;
import com.mum.groupproject.survey.domain.User;
import com.mum.groupproject.survey.iservice.IuserService;
import com.mum.groupproject.survey.utility.Md5;
import com.mum.groupproject.survey.utility.Messages;

@Service
@Transactional
public class UserService implements IuserService {

	@Autowired
	private AdminDao adminDao;

	@Autowired
	private SurveyTakerDao takerDao;

	@Override
	public String createAccount(User user) {
		try {
			if (user instanceof SurveyTaker) {
				SurveyTaker taker = (SurveyTaker) user;
				taker.setPassword(Md5.md5(taker.getPassword()));
				takerDao.create(taker);

			} else if (user instanceof Admin) {
				Admin admin = (Admin) user;
				adminDao.create(admin);
			}
			return Messages.save;
		} catch (Exception e) {
			return Messages.exception;
		}
	}

	@Override
	public String updateAccount(User user) {
		try {
			if (user instanceof SurveyTaker) {
				SurveyTaker taker = (SurveyTaker) user;
				takerDao.update(taker);

			} else if (user instanceof Admin) {
				Admin admin = (Admin) user;
				adminDao.update(admin);
			}
			return Messages.save;
		} catch (Exception e) {
			return Messages.exception;
		}
	}

	@Override
	public String deleteAccount(User user) {
		try {
			user.setDeleted(true);
			if (user instanceof SurveyTaker) {
				SurveyTaker taker = (SurveyTaker) user;
				takerDao.update(taker);

			} else if (user instanceof Admin) {
				Admin admin = (Admin) user;
				adminDao.update(admin);
			}
			return Messages.delete;
		} catch (Exception e) {
			return Messages.exception;
		}
	}

	@Override
	public String blockAccount(User user) {
		try {
			user.setActive(false);
			if (user instanceof SurveyTaker) {
				SurveyTaker taker = (SurveyTaker) user;
				takerDao.update(taker);

			} else if (user instanceof Admin) {
				Admin admin = (Admin) user;
				adminDao.update(admin);
			}
			return Messages.save;
		} catch (Exception e) {
			return Messages.exception;
		}
	}

	@Override
	public Admin findAdminByusername(String username) {
		return  adminDao.findByUsername(username);
	}

	@Override
	public SurveyTaker findTakerByuername(String username) {
		return takerDao.findTakerByUsername(username);
	}

}
