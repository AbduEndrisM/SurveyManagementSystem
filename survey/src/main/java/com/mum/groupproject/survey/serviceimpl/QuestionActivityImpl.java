package com.mum.groupproject.survey.serviceimpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mum.groupproject.survey.domain.QuestionActivity;
import com.mum.groupproject.survey.domain.Answer;
import com.mum.groupproject.survey.domain.Rate;
import com.mum.groupproject.survey.iservice.IAnswer;
import com.mum.groupproject.survey.iservice.IQuestionActivityService;
import com.mum.groupproject.survey.iservice.IRate;
import com.mum.groupproject.survey.utility.Messages;

import java.util.*;

@Service
@Transactional
public class QuestionActivityImpl implements IQuestionActivityService {

	@Autowired
	private IAnswer answerService;

	@Autowired
	private IRate rateService;

	@Override
	public String createQuestionActivity(List<QuestionActivity> activities) {
		try {
			for (QuestionActivity activity : activities) {
				if (activity instanceof Answer) {
					Answer ans = (Answer) activity;
					answerService.create(ans);
				} else {
					Rate rate = (Rate) activity;
					rateService.create(rate);
				}
			}
			return Messages.submitAnswer;
		} catch (Exception e) {
			e.printStackTrace();
			return Messages.exception;
		}
	}

}
