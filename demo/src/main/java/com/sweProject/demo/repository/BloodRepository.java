package com.sweProject.demo.repository;
import com.sweProject.demo.model.BloodInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BloodRepository extends JpaRepository<BloodInventory, Long> {
    // Additional custom query methods
    @Query("SELECT b FROM BloodInventory b WHERE b.bloodGroup = :bloodGroup")
    List<BloodInventory> findBloodByType(String bloodGroup);


    @Query("SELECT b FROM BloodInventory b WHERE b.bloodGroup = :bloodGroup AND b.bloodQuantity >= :bloodQuantity")
    List<BloodInventory> findByBloodGroupAndQuantity(String bloodGroup, int bloodQuantity);


    @Query("SELECT b FROM BloodInventory b WHERE b.bloodGroup = :bloodGroup")
    List<BloodInventory> findByBloodGroup(String bloodGroup);

    @Query("SELECT b FROM BloodInventory b WHERE b.bloodQuantity = :bloodQuantity")
    List<BloodInventory> findByQuantity(int bloodQuantity);

}
