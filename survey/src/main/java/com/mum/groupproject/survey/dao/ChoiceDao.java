package com.mum.groupproject.survey.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.mum.groupproject.survey.domain.Choice;
import com.mum.groupproject.survey.domain.Question;

@Repository
public class ChoiceDao extends GenericDao<Choice> {

	public List<Choice> allChoices() {
		List<Choice> list = new ArrayList<>();
		for (Choice choice : allObejcts()) {
			if (!choice.isDeleted()) {
				list.add(choice);
			}
		}
		return list;
	}

	public List<Choice> questionChoices(final Question question) {

		List<Choice> list = new ArrayList<>();
		for (Choice choice : allChoices()) {
			if (choice.getQuestion().equals(question)) {
				list.add(choice);
			}
		}
		return list;

	}

}
