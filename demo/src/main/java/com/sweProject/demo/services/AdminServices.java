package com.sweProject.demo.services;

import com.sweProject.demo.model.Admin;
import com.sweProject.demo.model.BloodInventory;
import com.sweProject.demo.model.Donor;
import com.sweProject.demo.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServices {

    @Autowired
    DonorService donorService;

    @Autowired
    AdminRepository adminRepository;


    public Admin registerAdmin(Admin admin) {

        donorService.validateEmail(admin.getEmail());
        donorService.validatePassword(admin.getPassword());

        return adminRepository.save(admin);
    }

    public Admin findByUsernameAndPassword(String username, String password) {
        return adminRepository.findByUsernameAndPassword(username, password);
    }
}
