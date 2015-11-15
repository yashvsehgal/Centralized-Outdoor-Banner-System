package com.neu.controller;

import java.util.ArrayList;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.neu.dao.HibernateUtil;
import com.neu.dao.MainDAO;
import com.neu.model.Advertiser;
import com.neu.model.Advertisements;
import com.neu.model.Booking;
import com.neu.model.Publisher;
import com.neu.model.User;



public class mainTest {
	
	public static void main(String args[]) throws Exception {
		
		/*Advertiser b = new Advertiser();
		Publisher pub = new Publisher();
		
		
		b.setName("Prateek");
		b.setCompany_name("Facebook");
		pub.setName("Bhanu");
		
		Advertisment air = new Advertisment();
		Advertisment air1 = new Advertisment();
		Advertisment air2 = new Advertisment();
		Advertisment air3 = new Advertisment();
		Advertisment air4 = new Advertisment();
		
		ArrayList<Advertisment> a1 = new ArrayList(); 
		
		Subscription one = new Subscription();
		
		Booking book1 = new Booking();
		
		book1.setAdvertiser_id(1);
		book1.setPublisher_id(2);
		book1.setAd(air1);
		
		one.setSubscription_status(true);
	
		air.setAvailable_from(new Date());
		air.setAvailable_till(new Date());
		air.setHeight(12);
		
		air1.setAvailable_from(new Date());
		air1.setAvailable_till(new Date());
		air1.setHeight(13);
		
		air3.setAvailable_from(new Date());
		air3.setAvailable_till(new Date());
		air3.setHeight(12);
		
		air4.setAvailable_from(new Date());
		air4.setAvailable_till(new Date());
		air4.setHeight(12);
		
		a1.add(air);
		a1.add(air1);
		a1.add(air3);
		a1.add(air4);
		
		pub.setAd_inventory(a1);
		pub.setSubscription(one);*/
		
	
	
		
		
		MainDAO dao = new MainDAO();
		
		Advertisements air = new Advertisements();
		
	
		
	
		/*session.save(b);
		session.save(book1);
		session.save(air);
		session.save(air1);
		session.save(air3);
		session.save(air4);
		session.save(pub);
		session.save(one);*/
		
		
		
		//dao.activateUser(user1);
		
		//System.out.println(user.get(0).getName());
		System.out.println("Success");
		
	}

}
