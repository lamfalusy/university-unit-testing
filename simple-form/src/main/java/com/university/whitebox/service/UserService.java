package com.university.whitebox.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.university.whitebox.model.User;
import com.university.whitebox.persistance.UserDao;

@Service
public class UserService {
    
    private UserDao userDao;
    
    @Autowired
    public UserService(UserDao userDao) {
        super();
        this.userDao = userDao;
    }

    public void createUser(User user) {
        if(user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if(user.getEmail() == null || user.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or blank");
        }
        if(user.getName() == null || user.getName().isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }

        if(userDao.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("The given Email Address has been already used");
        }
        
        userDao.save(user);
    }
    
    public void updateUser(User user) {
        if(user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if(user.getEmail() == null || user.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or blank");
        }
        if(user.getName() == null || user.getName().isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        
        Optional<Long> storedId = userDao.findByEmail(user.getEmail()).map(User::getId);
        
        if(storedId.isPresent() && !storedId.get().equals(user.getId())) {
            throw new IllegalArgumentException("The given Email Address has been already used");
        }
        
        userDao.save(user);
    }
    
    public void deleteUser(Long id) {
        if(id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        
        Optional<User> user = userDao.findById(id);
        if(user.isEmpty()) {
            throw new IllegalArgumentException("Invalid user Id:" + id);
        }
        
        userDao.delete(user.get());
    }
    
    public User getUser(Long id) {
        if(id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        
        Optional<User> user = userDao.findById(id);
        if(user.isEmpty()) {
            throw new IllegalArgumentException("Invalid user Id:" + id);
        }
        
        return user.get();
    }
    
    public Iterable<User> getUsers() {
        return userDao.findAll();
    }
    
}
