package com.egen.management.service.dao;

import java.util.List;

import com.egen.management.service.model.User;
/**
 * 
 * @author Karthik Selvaraj
 *
 */
public interface UserDao {
    
public boolean isUserExist(User user);
    
    public void saveUser(User user);
    
    public User findById(String id);
    
    public void updateUser(User user);
    
    public List<User> findAllUsers();

}
