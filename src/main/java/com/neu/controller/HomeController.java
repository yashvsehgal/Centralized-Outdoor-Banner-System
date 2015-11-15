package com.neu.controller;


import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.neu.dao.MainDAO;
import com.neu.model.Advertisements;
import com.neu.model.User;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private MainDAO mainDAO;
	
	@Autowired
	@Qualifier("userValidator")
	private Validator validator;
	


	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
	
		User user = new User();
		model.addAttribute("user",user);
	
		
	
		return "index";
		
		
	}
	
	@InitBinder("user")
	private void initBinder (WebDataBinder binder){
		binder.setValidator(validator);
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginerror(Model model,User user, BindingResult result) {
		
		return "index";
		
	}
	


	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model model,@Valid User user, BindingResult result, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User validatedUser = null;
		
		if(result.hasErrors()) {
		
			return "LoginError";
		}
		else
		
		try {
			 validatedUser =  mainDAO.queryUserByNameAndPassword(user.getUserName(),user.getPassword());
			 model.addAttribute("contextPath", request.getContextPath());
			 
			 if(validatedUser == null){
					
				 return "LoginError";
				}
			 
			 if(!validatedUser.isRegistrationStatus()) {
				 
				 return "RegistrationError";
			 }
				
		

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.setAttribute("user",validatedUser);
		
		model.addAttribute("name",user.getName());
		
		if(validatedUser.getType().equalsIgnoreCase("Publisher")) {
			
			return "publisher";
		}
		
		else if(validatedUser.getType().equalsIgnoreCase("Advertiser")) {
		model.addAttribute("advertisment", new Advertisements());
		return "advertiser";
		
		}
		
		else if(validatedUser.getType().equalsIgnoreCase("Admin")) {
			
			return "admin";
			
			}
		
		return "index";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session,HttpServletResponse response) {
		
		
		 session.invalidate();
		 response.setHeader("Cache-Control", "no-cache");
		 response.setDateHeader("Expires", 0);
         return "redirect:/";
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String getUnregUsers(Model model, HttpSession session) {		
		ArrayList <User> userList = new ArrayList();
		
		try {
			userList = mainDAO.populateUnApprovedUsers();
			model.addAttribute("userList",userList);
		   } catch (Exception e) {
		   	e.printStackTrace();
		   }
         return "UnapprovedUser";
	}
	
	@RequestMapping(value = "/approveAdvertisement", method = RequestMethod.GET)
	public String getUnApprovedAdvertisement(@ModelAttribute("model")ModelMap model, HttpSession session) {		
		ArrayList <Advertisements> advertisementList = new ArrayList<Advertisements>();		
		try {
			advertisementList = mainDAO.populateAdvertisement();
			model.addAttribute("advList",advertisementList);
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
         return "UnapprovedAdvertisement";
	}
	

	@RequestMapping(value = "/activateusers", method = RequestMethod.GET)
	public String activateUser(Model model,@RequestParam("userid") String userid) {		
		
		try {
			mainDAO.activateUser(userid);
		   } catch (Exception e) {
		   	e.printStackTrace();
		   }
        return "admin";
	}
	
	
	@RequestMapping(value = "/changePassword")
	public String changePasswordPage(Locale locale, Model model) {
         return "changePassword";
	}
	
	
	@RequestMapping(value = "/passwordChange")
	public String changePassword(Locale locale, Model model,HttpServletRequest request, @RequestParam("oldPassword") String oldPassword,@RequestParam("newPassword") String newPassword) {
		try {
			
			HttpSession session = request.getSession();		
			User user = (User) session.getAttribute("user");
			mainDAO.updatePassword(user,newPassword,oldPassword);
			
		   } catch (Exception e) {
		   	e.printStackTrace();
		   }
         return "changePasswordSuccess";
	}
	
	
	
}
