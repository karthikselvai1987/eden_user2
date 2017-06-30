package com.egen.management.service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egen.management.service.dao.UserDao;
import com.egen.management.service.model.User;

/**
 * 
 * @author Karthik Selvaraj
 *
 */
@Component("userService")
public class UserServiceImpl implements UserService {
    
    Logger logger = Logger.getLogger(UserServiceImpl.class);
    @Autowired
    private UserDao userdao= null;
    
    @Autowired
    private ValidatorFactory validatorFactory = null;
    
    /**
     * To check if user already exist in our database
     */
    @Override
    public boolean isUserExist(User user) {
	return userdao.isUserExist(user);
    }
    
    /**
     * To create a new user
     */
    @Override
    public void saveUser(User user) {
	userdao.saveUser(user);
    }

    /**
     * To find an user with given user id
     */
    @Override
    public User findById(String id) {
	return userdao.findById(id);
    }
    
    /**
     * To update an user
     */
    @Override
    public void updateUser(User user) {
	userdao.updateUser(user);
    }
    
    /**
     * To find list of all users
     */
    @Override
    public List<User> findAllUsers() {
	return userdao.findAllUsers();
    }
    
    /**
     * Validate user info using hibernate validation
     */
    public List<String> validateUserInfo(User user){
	List<String> errorMessages = new ArrayList<String>();
	String errorMessage = null;
	validatorFactory = Validation.buildDefaultValidatorFactory();
	Validator  validator = validatorFactory.getValidator();
	Set<ConstraintViolation<User>> validationConstraints = validator.validate(user);
	if(validationConstraints.isEmpty()){
	    return null;
	}else{
	    for(ConstraintViolation<User> constraint: validationConstraints){
		errorMessage = constraint.getMessage();
		errorMessages.add(errorMessage);
	    }
	}
	return errorMessages;
    }

}
