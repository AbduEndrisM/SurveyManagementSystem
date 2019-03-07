package com.mum.groupproject.survey.iservice;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.mum.groupproject.survey.dao.AdminDao;
import com.mum.groupproject.survey.domain.Admin;
import com.mum.groupproject.survey.domain.SurveyTaker;
import com.mum.groupproject.survey.domain.User;

@Service
@Transactional
public interface IuserService {
	
	
	String createAccount(final User user);
	
	String updateAccount(final User user);
	
	String deleteAccount(final User user);
	
	String blockAccount(final User user);
	
	
	Admin findAdminByusername(final String username);
	
	SurveyTaker findTakerByuername(final String username);
	
	

}
