package com.sweProject.demo.repository;

import com.sweProject.demo.model.Admin;
import com.sweProject.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {


    // Find a user by their ID
    Optional<User> findById(Long id);

    // Find all users
    List<User> findAll();

    // Delete a user by their ID
    void deleteById(Long id);
    
    User findByUsername(String username);

    // Find users by their email
    List<User> findByEmail(String email);

    @Query("SELECT a FROM User a WHERE a.username = :username AND a.password = :password")
    User findByUsernameAndPassword(String username, String password);


}
