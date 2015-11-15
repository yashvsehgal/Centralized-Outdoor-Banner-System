package com.neu.controller;


import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.neu.dao.MainDAO;
import com.neu.model.Advertiser;
import com.neu.model.Publisher;
import com.neu.model.User;

@Controller
public class RegistrationController {
	
	@Autowired
	private MainDAO mainDAO;
	
	
	@Autowired
	@Qualifier("registrationValidator")
	private Validator validator;
	
	@InitBinder
	private void initBinder (WebDataBinder binder){
		binder.setValidator(validator);
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerUser(Model model,@Validated User user,BindingResult result,@RequestParam("type") String type) {
	
		if(result.hasErrors()) {
			
			return "error";
			
		}
		
		Calendar calendar = Calendar.getInstance();
		Date currentDate = calendar.getTime();
		calendar.add(Calendar.YEAR,1);
		Date nextYear = calendar.getTime();
		
		if(type.equalsIgnoreCase("publisher")) {
			
			Publisher pub = new Publisher();
			
			pub.setUserName(user.getUserName());
			pub.setPassword(user.getPassword());
			pub.setName(user.getName());
			pub.setEmail(user.getEmail());
			pub.setContactNumber(user.getContactNumber());
			pub.setType(type);
			pub.setConfirmPassword(user.getPassword());
			pub.setSusbcriptionFrom(currentDate);
			pub.setSubscriptionTill(nextYear);
			
			try {
				
				mainDAO.addPublisher(pub);
			}catch(Exception e) {
				
				System.out.print("Error in adding pub");
			}
			
		}
		

		if (type.equalsIgnoreCase("advertiser")) {
		
		Advertiser advertiser = new Advertiser();
		System.out.println("I am creating a advertiser");
		advertiser.setUserName(user.getUserName());
		advertiser.setPassword(user.getPassword());
		advertiser.setName(user.getName());
		advertiser.setEmail(user.getEmail());
		advertiser.setType(user.getType());
		advertiser.setContactNumber(user.getContactNumber());
		advertiser.setConfirmPassword(user.getPassword());
		advertiser.setSusbcriptionFrom(currentDate);
		advertiser.setSubscriptionTill(nextYear);
		
		try {
			
			mainDAO.addAdvertiser(advertiser);
		}catch(Exception e) {
			
			System.out.print("Error in adding pub");
		}
		
	}
		
		return "index";
	}

}
