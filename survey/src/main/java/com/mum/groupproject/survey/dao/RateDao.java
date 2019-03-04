package com.mum.groupproject.survey.dao;

import org.springframework.stereotype.Repository;

import com.mum.groupproject.survey.domain.Rate;

import java.util.*;
@Repository
public class RateDao extends GenericDao<Rate>{

	
	public List<Rate> allRates(){
		List<Rate> list = new ArrayList<>();
		for(Rate rate:allObejcts()) {
			if(!rate.isDeleted()) {list.add(rate);}
		}
		return list;
	}
}
