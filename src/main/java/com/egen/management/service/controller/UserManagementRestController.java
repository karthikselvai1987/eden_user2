package com.egen.management.service.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.egen.management.service.model.User;
import com.egen.management.service.service.UserService;
/**
 * 
 * @author Karthik Selvaraj
 * Rest Controller class to expose CRUD operations
 *
 */
@RestController
public class UserManagementRestController {
    
    Logger logger = Logger.getLogger(UserManagementRestController.class);
    @Autowired
    UserService userService;
    
    /**
     * Create user service
     * @param userInfo
     * @param ucBuilder
     * @return
     */
    @RequestMapping(value = "/user/createUser", method = RequestMethod.POST)
    public ResponseEntity<List<String>> createUser(@RequestBody User userInfo, UriComponentsBuilder ucBuilder) {
	
	
	List<String> errorMessages = userService.validateUserInfo(userInfo);
	if(null!= errorMessages && errorMessages.size()>0){
	    
	    return new ResponseEntity<List<String>>(errorMessages, HttpStatus.EXPECTATION_FAILED);
	}else{
	    
	    if (userService.isUserExist(userInfo)) {
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	    }

	    userService.saveUser(userInfo);

	    HttpHeaders headers = new HttpHeaders();
	    headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(userInfo.getId()).toUri());
	    return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}
	
       
    
    }
    
    /**
     * Update user service
     * @param userInfo
     * @return
     */
    @RequestMapping(value = "/user/updateUser", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@RequestBody User userInfo) {
	
	List<String> errorMessages = userService.validateUserInfo(userInfo);
	if(null!= errorMessages && errorMessages.size()>0){
	    
	    return new ResponseEntity<User>(userInfo, HttpStatus.EXPECTATION_FAILED);
	}
	User currentUser = userService.findById(userInfo.getId());
         
        if (currentUser==null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        currentUser = updateCurrentUserInfo(currentUser,userInfo);
        userService.updateUser(currentUser);
        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
        
    }
    
    /**
     * List All users service
     * @return
     */
    @RequestMapping(value = "/user/getAllUsers", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        if(users.isEmpty()){
            return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }
 
    /**
     * Updates current user with the user in system
     * @param currentUser
     * @param userInfo
     * @return
     */
    private User updateCurrentUserInfo(User currentUser, User userInfo) {
	currentUser.setFirstName(userInfo.getFirstName());
	currentUser.setLastName(userInfo.getLastName());
	currentUser.setAge(userInfo.getAge());
   	currentUser.setGender(userInfo.getGender());
   	currentUser.setMiddleName(userInfo.getMiddleName());
   	currentUser.setPhoneNumber(userInfo.getPhoneNumber());
   	currentUser.setZipCode(userInfo.getZipCode());
   	return currentUser;
       }

}
