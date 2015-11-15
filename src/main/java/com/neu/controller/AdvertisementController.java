package com.neu.controller;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.neu.dao.MainDAO;
import com.neu.model.Advertisements;
import com.neu.model.Publisher;
import com.neu.model.User;

@Controller
public class AdvertisementController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private MainDAO userDao;
	
	//Method to return the advertisement form for posting the ad by the publisher
	@RequestMapping(value = "/addAdvert", method = RequestMethod.POST)
	public String insertAd (Model model,HttpServletRequest request, Advertisements advertisement){			
			return "AddAd";
	}
	
	//Method to add the advertisement posted by the publisher
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String home(Model model, HttpServletRequest request,Advertisements advertisements){
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");	
		Publisher publisher = (Publisher)session.getAttribute("user");	
		advertisements.setPublisher(publisher);
		advertisements.setOwner(user.getName());
		publisher.getAdvertisementDetails().add(advertisements);
				
		try {
			  userDao.addAdvertisement(advertisements);			  
		    } 
		
		catch (Exception e) {
		    	e.printStackTrace();
		    }
         return "publisher";
	}
	
	//Method to view the list of advertisements posted by the publisher
	@RequestMapping(value = "/listad", method = RequestMethod.POST) 
	public String listad (Model model,HttpServletRequest request){	
	HttpSession session = request.getSession();	
	User user = (User) session.getAttribute("user");
	System.out.println("Inside the listad method");
	System.out.println(user.getName());
	ArrayList<Advertisements> adList = new ArrayList<Advertisements>();	
		try {
			adList = userDao.populateAdvertisementForPublisher(user.getId());
			logger.info("Number of advertisements is "+adList.size());
			model.addAttribute("adList",adList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "PopulateAdvert";
		
	}
	
	@RequestMapping(value = "/searchLoc", method = RequestMethod.POST)
	public String searchLocation (Model model,HttpServletRequest request, Advertisements advertisement){				
		return "SearchAds";
	}
	
	@RequestMapping(value = "/activateAds", method = RequestMethod.GET)
	public String activateAdvertisement(Model model, @RequestParam("advertid") int advertisementId) {		
	try {
		   userDao.activateAds(advertisementId);
	} 
	catch (Exception e) {
		   e.printStackTrace();
	}
			
	return "admin";
}
	
	@RequestMapping(value = "/searchAd", method = RequestMethod.POST) 
	public String searchResults (Model model,HttpServletRequest request,@RequestParam("location") String location){	
		System.out.println("Inside the search Result method");
		try {
			ArrayList<Advertisements> searchAdList = new ArrayList<Advertisements>();	
			searchAdList = userDao.searchAd(location);
			logger.info("Number of advertisements is "+searchAdList.size());
			model.addAttribute("searchadsList",searchAdList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "SearchAds";
		
	}
	
	@RequestMapping(value = "/viewAd") 
	public String viewAd(Model model,HttpServletRequest request,@RequestParam("adid") int ad_Id){
			
			HttpSession session = request.getSession();
			//ArrayList<Advertisements> searchadsList = new ArrayList<Advertisements>();		
			User user = (User) session.getAttribute("user");
			
			//System.out.println("Search Ad method executed");
			//System.out.println(location);
				
				try {
					Advertisements ads = userDao.populateFullAdInfo(ad_Id);
					model.addAttribute("ads",ads);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return "viewFullAd";
				
			}
		
	@RequestMapping(value = "/finalBooking", method = RequestMethod.POST) 
	public String finalBooking(Model model,HttpServletRequest request,@RequestParam("week") int weeks, @RequestParam("ad_Id") int ad_Id){
			
		   //System.out.println(weeks);
		   //System.out.println(ad_Id);
		
			HttpSession session = request.getSession();		
			User user = (User) session.getAttribute("user");
			//Advertisements advertisement = (Advertisements)session.getAttribute("advertisements");
				try {
					Date fromDate = userDao.forAvailableFromDate(ad_Id);
					userDao.updateAdvertisements(user,weeks,ad_Id);
					userDao.updateBookingData(user, weeks, ad_Id,fromDate);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return "bookingSuccess";
				
			}
}
