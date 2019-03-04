package com.mum.groupproject.survey.iservice;

import com.mum.groupproject.survey.domain.Rate;
import java.util.*;

public interface IRate {
	
	
	String create(final Rate rate);
	
	String update(final Rate rate);
	
	String delete(final Rate rate);

}
