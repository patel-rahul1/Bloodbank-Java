package com.sweProject.demo.repository;
import com.sweProject.demo.model.Donor;
import com.sweProject.demo.model.User;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonorRepository extends JpaRepository<Donor, Long> {

    // Define additional custom query methods if needed
    //List<Donor> findByBloodGroup(String bloodGroup);

    @Query("SELECT a FROM Donor a WHERE a.username = :username AND a.password = :password")
    Donor findByUsernameAndPassword(String username, String password);

    @Query("SELECT d FROM Donor d WHERE d.username = :username")
    Donor findByUsername(String username);



    // You can add more custom query methods based on your requirements
}