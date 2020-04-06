package com.university.whitebox.persistance;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.university.whitebox.model.User;

@Repository
public interface UserDao extends CrudRepository<User, Long> {

    Optional<User> findByEmail(String email);
    
    boolean existsByEmail(String email);
    
}
