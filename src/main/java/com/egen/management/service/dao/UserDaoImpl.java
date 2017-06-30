package com.egen.management.service.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.egen.management.service.model.User;
import com.egen.management.service.util.HibernateUtil;

/**
 * 
 * @author Karthik Selvaraj
 *
 */
@Repository("userdao")
public class UserDaoImpl implements UserDao  {
    
    Logger logger = Logger.getLogger(UserDaoImpl.class);
    
  
    private SessionFactory  sessionFactory  = HibernateUtil.getSessionFactory();
    
    
    
    /**
     * To Check for an existing user
     */
    @Override
    public boolean isUserExist(User user) {
	String hql = "from User where id = :id";
	Session session = this.sessionFactory.getCurrentSession();
	try{
	    if(sessionFactory != null){
		org.hibernate.Query query = session.createQuery(hql);
		query.setString("id", user.getId());
		List<User> users = (List<User>)query.list();
		if(null!=users && !users.isEmpty()){
		    return true;
		}

	    }
	}catch(Exception e){
	    logger.error("Exception while finding user data", e);
	}
	
	finally{
	    if(session.isOpen())
		session.close();
	}
	
	return false;
    }
    
    /**
     * To create a new user
     */
    @Override
    @Transactional
    public void saveUser(User user) {
	
	logger.info("Entering save user method");
	if(user == null){
	    return;
	}
	 Session session = sessionFactory.openSession();
	try{
	    if(sessionFactory != null){
		session.save(user);
		session.close();
	    }   
	}catch(Exception e){
	    logger.error("Exception while creating user data", e);
	}
	
	finally{
	    if(session.isOpen())
		session.close();
	}
    }
    
    /**
     * To find a user details with user id
     */
    @Override
    @Transactional
    public User findById(String id) {
	String hql = "from User where id = :id";
	Session session = this.sessionFactory.getCurrentSession();
	try{
	    if(sessionFactory != null){
		Query query = session.createQuery(hql);
		query.setString("id", id);
		List<User> users = (List<User>)query.list();
		if(null!=users && !users.isEmpty()){
		    return users.get(0);
		}

	    }
	}catch(Exception e){
		    logger.error("Exception while finding user data", e);
	}
	
	finally{
	    if(session.isOpen())
		session.close();
	}
	
	return null;
    }
    
    /**
     * To update an already exisiting user
     */
    @Override
    @Transactional
    public void updateUser(User user) {
	Session session = this.sessionFactory.getCurrentSession();
	session.update(user);
    }
    
    /**
     * To get all list of user from database
     */
    @Override
    @Transactional
    public List<User> findAllUsers() {
	Session session = this.sessionFactory.getCurrentSession();
	List<User> users = session.createQuery("from User").list();
	return users;
    }

}
