package com.neu.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.neu.dao.HibernateUtil;
import com.neu.model.Advertisements;
import com.neu.model.Advertiser;
import com.neu.model.Booking;
import com.neu.model.Publisher;
import com.neu.model.User;

public class MainDAO extends DAO{
	
	ArrayList<User> userList = new ArrayList<User>();

	private SessionFactory sessionFactory;
	private Session session;
	
	//Method to authenticate the user 
	public User queryUserByNameAndPassword(String name, String password) throws Exception {
		try {
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
            Query q = getSession().createQuery("from User where userName = :username and password = :password");
           // q.setCacheable(true);
            //q.setCacheRegion("User");
            q.setString("username", name);
            q.setString("password", password);
            User user = (User) q.uniqueResult();
            session.close();
            return user;
		} catch (HibernateException e) {
            throw new Exception("Could not get user " + name, e);
		}
    }
	
/*************Method for Adding advertiser, publisher and Advertisement***********************************/
	
	// Adding a new advertiser
	
	public void addAdvertiser(Advertiser advertiser) throws Exception{
		try{
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(advertiser);
			session.getTransaction().commit();
            session.close();			
		}
		
		catch (HibernateException e) {
            throw new Exception("Cannot add the advertiser", e);
		}
	}
	
	
	// Adding a new publisher
	
	public void addPublisher(Publisher publisher) throws Exception{
		try{
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(publisher);
			session.getTransaction().commit();
            session.close();
		}
		
		catch (HibernateException e) {
            throw new Exception("Cannot add the publisher", e);
		}
	}
	
	// Adding a new advertisements
	
		public void addAdvertisement(Advertisements advertisements) throws Exception{
			try{
				sessionFactory = HibernateUtil.getSessionFactory();
				session = sessionFactory.openSession();
				session.beginTransaction();
				session.save(advertisements);
				session.getTransaction().commit();
	            session.close();
			}
			
			catch (HibernateException e) {
	            throw new Exception("Cannot add the advertisement", e);
			}
		}
		
		//Associating advertisement with the publisher
		public void associateAdvertisement(Publisher publisher,Advertisements advertisements) throws Exception{
			Transaction tx = null;
			Session session =getSession();
			System.out.println("Inside the association method");
			try{
				tx = session.beginTransaction();	
				publisher.getAdvertisementDetails().add(advertisements);
				session.update(publisher);
				tx.commit();
	            close();
			}
			
			catch (HibernateException e) {
	            throw new Exception("Cannot add the publisher", e);
			}
		}
		
		
/********************Methods to populate the un approved users and advertisements****************************/		
	
	//Method to fetch the user list whose registration status has to be approved
	
	public ArrayList<User> populateUnApprovedUsers() throws Exception{			
		
		try{
			ArrayList<User>userList = new ArrayList<User>();
			Query query = getSession().createQuery("from User where registrationStatus=0");
			userList = (ArrayList<User>)query.list();
			System.out.println("Number of users to be approved is "+userList.size());
			close();
			return userList;
		}catch(HibernateException e){
			e.printStackTrace();
		}
		return userList;
	}
	
	//Method to populate the advertisements to be approved
	
		public ArrayList<Advertisements> populateAdvertisement() throws Exception{
				
			ArrayList<Advertisements> adsList = new ArrayList<Advertisements>();
			Transaction tx = null;
	    	Session session = getSession();
			    try{
				    tx = session.beginTransaction();
				    Query query = getSession().createQuery("from Advertisements where status=0");
				    adsList = (ArrayList<Advertisements>)query.list();
				    System.out.println("Number of advertisements to be approved "+adsList.size());
				    tx.commit();
				    close();
				    return adsList;
				
			     }catch(HibernateException e){
				    if (tx!=null) tx.rollback();
				    throw new Exception("Could not fetch Ads details" + e);
			     }
	    }
	
/*****************************List of objects to be approved********************************************/	

	// Method to approve the user by the admin
	
	public void activateUser(String userid) throws Exception{
		try{	
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();	
			Query query = session.createQuery("FROM User where username = :userid");
			query.setString("userid",userid);
			User user = (User) query.uniqueResult();
			user.setRegistrationStatus(true);
			tx.commit();
			close();
			}
		catch(HibernateException e){				
				e.printStackTrace();
				throw new Exception("Error in approving User");
			}
     }
	
	// Method to approve the advertisement by the administrator
	
    public void activateAds(int adId) throws Exception{	  	
 	   try {
 		   	 sessionFactory = HibernateUtil.getSessionFactory();
			 session = sessionFactory.openSession();
			 Transaction tx = session.beginTransaction();	
		     Query query = session.createQuery("FROM Advertisements where Id = :adId");	     
		     query.setInteger("adId", adId);
		     Advertisements ads = (Advertisements) query.uniqueResult();
		     System.out.println("Advertisement yet to be approved");
		     ads.setStatus(true);
		     System.out.println("Advertisement approved");
		     tx.commit();
		     close();	     
	       }   
 	   
 	   		catch(HibernateException e){				
			e.printStackTrace();
			throw new Exception("Error in approving Ads");
	     }	
     }

/**************List of objects to be populated on the basis of inputs***********************************/    
    public ArrayList<Advertisements> populateAdvertisementForPublisher(int id) throws Exception{       
    	Transaction tx = null;
    	try{
			ArrayList<Advertisements> adOfPublisherList = new ArrayList<Advertisements>();
	    	Session session = getSession();
			tx = session.beginTransaction();
			Query query = getSession().createQuery("from Advertisements where publisherId =:id");
			query.setInteger("id",id);
			adOfPublisherList = (ArrayList<Advertisements>)query.list();
			System.out.println("Number of advertisements published "+adOfPublisherList.size());
			tx.commit();
			close();
			return adOfPublisherList;
		}catch(HibernateException e){
			if (tx!=null) tx.rollback();
			throw new Exception("Could not fetch Ads" + e);
		}
    }
    
    //Method to populate booking history of an advertiser
    public ArrayList<Booking> populateBookingsByAdvertiser(int id) throws Exception{
    	
    	ArrayList<Booking> bookingList = new ArrayList<Booking>();
    	Transaction tx = null;
     	Session session = getSession();
    	try{
    		tx = session.beginTransaction();
    		Query query = getSession().createQuery("from Booking where advertiserId=:id");
    		query.setInteger("id", id);
    		bookingList = (ArrayList<Booking>)query.list();
    		tx.commit();
			return bookingList;
		   }catch(HibernateException e){
			if (tx!=null) tx.rollback();
			throw new Exception("Could not fetch Booking Details" + e);
		}
    }
    
 public ArrayList<String> populatePublisherName(int publisherId) throws Exception{
    	
    	ArrayList<String> publisherNameList = new ArrayList<String>();
    	Transaction tx = null;
     	Session session = getSession();
    	try{
    		tx = session.beginTransaction();
    		Query query = getSession().createQuery("Select name from User where Id=:pid");
    		query.setInteger("pid", publisherId);
    		publisherNameList = (ArrayList<String>)query.list();
    		System.out.println(publisherNameList.size());
    		tx.commit();
			return publisherNameList;
		   }
    	catch(HibernateException e){
			if (tx!=null) tx.rollback();
			throw new Exception("Could not fetch Booking Details" + e);
		}
    }

 /*********************Search for Location*******************************************************/ 
 
 public ArrayList<Advertisements> searchAd(String location) throws Exception{
 	
	    System.out.println("Inside the search method");
	    ArrayList<Advertisements> adsForAdvertiserList = new ArrayList<Advertisements>();
	    Transaction tx = null;
	    Session session = getSession();	
		try{
			tx = session.beginTransaction();
  		Query query = getSession().createQuery("from Advertisements where location=:loc AND status=1");
  		query.setString("loc", location);
  		adsForAdvertiserList = (ArrayList<Advertisements>)query.list();   		
  		tx.commit();	
  		close();
  		
		   }catch(HibernateException e){
			if (tx!=null) tx.rollback();
			throw new Exception("Could not fetch Ads Details" + e);
		   }
		return adsForAdvertiserList;
		
  }
 
 /************************Method to update the password******************************/	
	//Method for updating password
	public void updatePassword(User user, String newPassword,String oldPassword) throws Exception {
		try {
			Transaction tx1 = null;
			session = getSession();
			tx1 = session.beginTransaction();
			user.setPassword(newPassword);
			session.update(user);
			tx1.commit();
			close();
		} catch (HibernateException e) {
				throw new Exception("Cannot update password");
		}
	}

/****************************Method to book the Ad************************************/ 	
	//Method to book the advertisement published by the publisher
	public void bookingAd(int advertisementId, Advertiser advertiser) throws Exception{	  	
	 	   try {
	 		   	 System.out.println("Inside Booking Method");
	 		   	 sessionFactory = HibernateUtil.getSessionFactory();
				 session = sessionFactory.openSession();
				 Transaction tx = session.beginTransaction();
								 
			     Query query = session.createQuery("FROM Advertisements where Id = :adId");	     
			     query.setInteger("adId", advertisementId);
			     Advertisements ads = (Advertisements) query.uniqueResult();
			     
				 Booking booking = new Booking();
				 booking.setAdvertiser(advertiser);
				 booking.setAdvertisementId(ads.getAdvertisementId());
				 booking.setPublisherId(ads.getPublisher().getId());
				 booking.setFromBookedDate(new Date());
				 booking.setTillBookedDate(ads.getAvailableTill());
			     advertiser.getBookingAdvertisementList().add(booking);		
			     ads.setBookedBy(advertiser.getName());
			     ads.setAvailableFrom(booking.getTillBookedDate());
			     ads.setAvailableTill(null);
			     session.save(booking);
			     session.update(ads);
			     System.out.println("Booking successful");
			     tx.commit();
			     close();	     
		       }   
	 	   
	 	   		catch(HibernateException e){				
				e.printStackTrace();
				throw new Exception("Error in Booking Ads");
		     }	
	     }	
	
	public Advertisements populateFullAdInfo(int ad_Id) throws Exception{
		  
		Transaction tx = null;
	  	Session session = getSession();
	  	Advertisements ads = null;
	   try {
		    tx = session.beginTransaction();
		    Query query = session.createQuery("FROM Advertisements where Id = :ad_Id");	    
		    query.setInteger("ad_Id",ad_Id);
		    ads = (Advertisements) query.uniqueResult();
		    tx.commit();
		    close();
		    return ads;
	   }catch(HibernateException e) {
		      if(tx != null) 
				   tx.rollback();
		      e.printStackTrace();
		      }
	   
		return ads;      
   }
   
   public void updateBookingData(User user,int weeks, int ad_Id, Date fromDate) throws Exception{
	  
	Transaction tx = null;
   	Session session = getSession();	
   	int days = 7*weeks;
	
    try {
	    tx = session.beginTransaction();
	    int id = user.getId();
	    System.out.println(id);
	    Advertiser advertiser = (Advertiser)user;
	    //Query query = session.createQuery("FROM booking where ad_Id = :ad_Id");	    
	    //query.setInteger("ad_Id",ad_Id);
	    
	   // Date fromDate = forAvailableFromDate(ad_Id);
	    
		System.out.println("Inside booking dao method");
		Calendar c = Calendar.getInstance(); 
		c.setTime(fromDate); 
		c.add(Calendar.DATE, days);
		Date tillDate = c.getTime();
		System.out.println(tillDate);
		 
	    Date bookingFromDate = fromDate;
	    Date bookingtillDate = tillDate;
	   
	    Booking booking = new Booking();
	    booking.setFromBookedDate(bookingFromDate);
	    booking.setTillBookedDate(bookingtillDate);
	    booking.setAdvertiser(advertiser);
	    booking.setAdvertisementId(ad_Id);
	    int Id = forPublisherId(ad_Id);
	    booking.setPublisherId(Id);
	    
	    session.save(booking);
	    tx.commit();
	    close();
	    
      }  catch (HibernateException e) {
	      if(tx != null) {
		   tx.rollback();
	  }
   	
   }

    
   }
   
   
 public Date forAvailableFromDate(int ad_Id) throws Exception{
	  
	  Transaction tx = null;
	  	Session session = getSession();	
		Advertisements ads = null;
		Date fromDate=null;
	  try{
		  tx = session.beginTransaction();
		  Query query = session.createQuery("FROM Advertisements where Id = :ad_Id");
		  query.setInteger("ad_Id",ad_Id);
		  ads = (Advertisements) query.uniqueResult();
		  fromDate= ads.getAvailableFrom();
		  tx.commit();
		  close();
		  return fromDate;   
	  }catch(HibernateException e) {
	      if(tx != null) {
		   tx.rollback();
	  }
   }
	return fromDate;   
}
   
   public int forPublisherId(int ad_Id) throws Exception{
	  
	  Transaction tx = null;
	  	Session session = getSession();	
		Advertisements ads = null;
		int pubId=0;
	  try{
		  tx = session.beginTransaction();
		  Query query = session.createQuery("FROM Advertisements where Id = :ad_Id");
		  query.setInteger("ad_Id",ad_Id);
		  ads = (Advertisements) query.uniqueResult();
		  pubId= ads.getPublisher().getId();
		  tx.commit();
		  close();
		  return pubId;   
	  }catch(HibernateException e) {
	      if(tx != null) {
		   tx.rollback();
	  }
   }
	return pubId;   
}
   
   public void updateAdvertisements(User user,int weeks, int ad_Id) throws Exception{
	  
		Transaction tx = null;
	  	Session session = getSession();	
	  	int days = 7*weeks;
		
	   try {
		    tx = session.beginTransaction();
		    //Query query = session.createQuery("FROM booking where ad_Id = :ad_Id");	    
		    //query.setInteger("ad_Id",ad_Id);
			Date fromDate = forAvailableFromDate(ad_Id);
		    
			Calendar c = Calendar.getInstance(); 
			c.setTime(fromDate); 
			c.add(Calendar.DATE, days);
			Date froomDate = c.getTime();
			 
			 
			 
		    Date availableFromDate = froomDate;
		    
		    Query query = session.createQuery("FROM Advertisements where Id = :ad_Id");	    
		    query.setInteger("ad_Id",ad_Id);
		    Advertisements ads = (Advertisements) query.uniqueResult();
		    ads.setBookedBy(user.getName());
		    ads.setAvailableFrom(availableFromDate);
		    ads.setAvailableTill(null);
		    session.update(ads);
		    tx.commit();
		    close();
	   }catch (HibernateException e) {
		      if(tx != null) {
			   tx.rollback();
		  }
	  	
	  }
	  }   
	

}
