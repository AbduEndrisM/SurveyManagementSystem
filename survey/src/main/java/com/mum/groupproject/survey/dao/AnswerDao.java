package com.mum.groupproject.survey.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mum.groupproject.survey.domain.Answer;
import com.mum.groupproject.survey.domain.Choice;
import com.mum.groupproject.survey.domain.Question;

import java.util.*;

@Repository
public class AnswerDao extends GenericDao<Answer> {

	@Autowired
	private ChoiceDao choiceDao;

	public List<Answer> allAnswers() {
		List<Answer> list = new ArrayList<>();
		for (Answer answer : allObejcts()) {
			if (!answer.isDeleted()) {
				list.add(answer);
			}
		}
		return list;
	}

	public List<Answer> questionAnswers(final Question question) {
		List<Answer> list = new ArrayList<>();
		for (Answer answer : allAnswers()) {
			if (answer != null && answer.getQuestion() != null) {
				if (question != null) {
					if (answer.getQuestion().getId().equals(question.getId())) {
						list.add(answer);
					}
				}
			}
		}
		return list;
	}

	public Map<Choice, Integer> questionChoiceOccurence(final Question question) {
		int occurence = 0;
		List<Answer> answers = questionAnswers(question);
		List<Choice> questionChoices = choiceDao.questionChoices(question);
		Map<Choice, Integer> map = new HashMap<>();

		for (Choice choice : questionChoices) {
			for (Answer answer : answers) {
				if (answer.getValue().equals(choice.getValue())) {
					if (map.containsKey(choice)) {
						map.replace(choice, map.get(choice), map.get(choice) + 1);
					} else {
						map.put(choice, 1);
					}
					occurence++;
				}
			}
		}
		for (Choice choice : questionChoices) {

			if (!map.containsKey(choice)) {
				map.put(choice, 0);
			} else {
				int percentage = ((map.get(choice) * 100) / occurence);
				map.replace(choice, map.get(choice), percentage);
			}
		}
		
		return map;
	}

}
