package com.egen.management.service.service;

import java.util.List;

import com.egen.management.service.model.User;
/**
 * 
 * @author Karthik Selvaraj
 *
 */
public interface UserService {
    
    public boolean isUserExist(User user);
    
    public void saveUser(User user);
    
    public User findById(String id);
    
    public void updateUser(User user);
    
    public List<User> findAllUsers();
    
    public List<String> validateUserInfo(User user);

}
