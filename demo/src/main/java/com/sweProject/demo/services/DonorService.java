package com.sweProject.demo.services;

import com.sweProject.demo.model.BloodInventory;
import com.sweProject.demo.model.Donor;
import com.sweProject.demo.repository.BloodRepository;
import com.sweProject.demo.repository.DonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DonorService {

    @Autowired
    private DonorRepository donorRepository;


    @Autowired
    private BloodRepository bloodRepository;

    // Service method to register a new donor
    public Donor registerDonor(Donor donor) {

        validateUsername(donor.getUsername());
        validatePassword(donor.getPassword());
        BloodInventory bloodInventory1=new BloodInventory();
        bloodInventory1.setBloodGroup(donor.getBloodGroup());
        bloodInventory1.setDonorId(donor.getDonorId());
        bloodInventory1.setBloodQuantity(donor.getQuantity());
        bloodInventory1.setDonationDate(donor.getLastDonationDate());
        bloodRepository.save(bloodInventory1);

        return donorRepository.save(donor);
    }

    private void validateUsername(String username) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        // You can add more validations for username if needed
    }

    public void validatePassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
    }

    public boolean validateEmail(String email) {
        // Perform email validation logic here
        if (email == null || email.isEmpty()) {
            return false; // Email is empty
        }
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    public boolean validatePhoneNumber(String phoneNumber) {
        // Perform phone number validation logic here
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return false; // Phone number is empty
        }
        String phoneRegex = "^\\+(?:[0-9] ?){6,14}[0-9]$";
        if (!phoneNumber.matches(phoneRegex)) {
            return false; // Phone number does not match the regex pattern
        }
        return true; // Phone number is valid
    }


}
