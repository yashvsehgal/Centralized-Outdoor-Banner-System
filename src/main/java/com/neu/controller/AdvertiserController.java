package com.neu.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;






import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.neu.dao.MainDAO;
import com.neu.model.Advertisements;
import com.neu.model.Advertiser;
import com.neu.model.Booking;
import com.neu.model.User;

@Controller
public class AdvertiserController {
	
	@Autowired
	private MainDAO mainDAO;
	
	@RequestMapping(value = "/addadvert", method = RequestMethod.POST) 
	public String add_advert(Model model,HttpServletRequest request,Advertisements ad){
		
		HttpSession session = request.getSession();
		
		User user = (User) session.getAttribute("user");
		
		ad.setOwner(user.getName());
		
		try {
			mainDAO.addAdvertisement(ad);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "added";
		
	}
	
	@RequestMapping(value = "/addadform", method = RequestMethod.GET) 
	public String add_Ad(Model model, Advertisements ad){
		
		
		
		return "addadvert";
		
	}
	

	
	@RequestMapping(value = "/bookingHistory", method = RequestMethod.POST) 
	public String listad (Model model,HttpServletRequest request){	
	HttpSession session = request.getSession();	
	User user = (User) session.getAttribute("user");
	
	ArrayList<Booking> bookList = new ArrayList<Booking>();	
	ArrayList<String> pnameList = new ArrayList<String>();
	ArrayList<String> nameList = new ArrayList<String>();
		try {
			bookList = mainDAO.populateBookingsByAdvertiser(user.getId());
			
			model.addAttribute("bookingHistoryList",bookList);
			
			for(Booking b : bookList){			
			nameList = mainDAO.populatePublisherName(b.getPublisherId());
			pnameList.addAll(nameList);
			}
			
			model.addAttribute("list",pnameList);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return "BookingHistory";		
	}
	
	@RequestMapping(value = "/booking", method = RequestMethod.GET)
	public String bookingAdvertisement(Model model, HttpServletRequest request,@RequestParam("advertid") int advertisementId) {		
		HttpSession session = request.getSession();	
		Advertiser advertiser = (Advertiser) session.getAttribute("user");
		System.out.println(advertisementId);
		System.out.println(advertisementId);
		try {					
			mainDAO.bookingAd(advertisementId,advertiser);
		} 
		catch (Exception e) {
		    	e.printStackTrace();
		}
		
         return "Booked";
	}
}


