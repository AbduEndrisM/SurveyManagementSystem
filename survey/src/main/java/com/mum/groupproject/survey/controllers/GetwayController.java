package com.mum.groupproject.survey.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.mum.groupproject.survey.iservice.ISurvey;
import com.mum.groupproject.survey.utility.Test;

@RestController
@RequestMapping("/api")
public class GetwayController {

	@Autowired
	private ISurvey serveyServce;

	@RequestMapping(value = "/survey", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseBean create(@RequestBody Test test) {
		ResponseBean bean = new ResponseBean();
		try {
			System.out.println("New request coming from abdoul");
			System.out.println(test.getName() + " " + test.getDescription());
			// return serveyServce.create(survey);

			bean.code = "200";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}

}
