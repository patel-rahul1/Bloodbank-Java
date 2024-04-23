package com.sweProject.demo.repository;

import com.sweProject.demo.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    // Additional custom query methods
   // Optional<Admin> findByUsername(String username);

    @Query("SELECT a FROM Admin a WHERE a.username = :username AND a.password = :password")
    Admin findByUsernameAndPassword(String username, String password);
}