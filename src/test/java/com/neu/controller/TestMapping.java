package com.neu.controller;

import org.hibernate.Session;

import com.neu.dao.HibernateUtil;
import com.neu.model.Advertisements;
import com.neu.model.Advertiser;
import com.neu.model.Booking;
import com.neu.model.Publisher;
import com.neu.model.User;

public class TestMapping {
	public static void main(String args[]){
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		/*User user = new User();
		user.setName("admin");
		user.setUsername("admin");
		user.setPassword("admin");
		session.save(user);*/
		
		Publisher publisher = new Publisher();
		publisher.setName("Bhanu");
		publisher.setUserName("bhanu");
		publisher.setPassword("bhanu");
		publisher.setConfirmPassword("bhanu");
		publisher.setType("Publisher");
		publisher.setContactNumber("6173730287");
		publisher.setEmail("pratapbhanu6@gmail.com");
		publisher.setTotalAdPosted(2);
		session.persist(publisher);
				
		Advertiser advertiser = new Advertiser();
		advertiser.setName("yash");
		advertiser.setUserName("yash");
		advertiser.setPassword("yash");
		advertiser.setConfirmPassword("yash");
		advertiser.setCompanyname("ECW");
		advertiser.setType("Advertiser");
		advertiser.setContactNumber("6173730594");
		advertiser.setEmail("yashvsehgal@gmail.com");
		session.persist(advertiser);
		
		
		Advertisements advertisement = new Advertisements();
		advertisement.setLocation("Delhi");
		advertisement.setPublisher(publisher);		
		publisher.getAdvertisementDetails().add(advertisement);		
		session.persist(advertisement);
		
		
		Booking booking = new Booking();
		booking.setAdvertiser(advertiser);
		booking.setPublisherId(advertisement.getPublisher().getId());
		booking.setAdvertisementId(advertisement.getAdvertisementId());
		advertiser.getBookingAdvertisementList().add(booking);
		session.persist(booking);
		
		session.getTransaction().commit();
		session.close();
		System.out.println("success");
		
	}
}
