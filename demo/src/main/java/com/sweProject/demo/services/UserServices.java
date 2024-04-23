package com.sweProject.demo.services;

import com.sweProject.demo.model.Admin;
import com.sweProject.demo.model.BloodInventory;
import com.sweProject.demo.model.User;
import com.sweProject.demo.repository.BloodRepository;
import com.sweProject.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServices {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DonorService donorService;

    @Autowired
    BloodRepository bloodRepository;


    public User registerUser(User user) {

        donorService.validateEmail(user.getEmail());
        donorService.validatePassword(user.getPassword());




        return userRepository.save(user);
    }

    public Boolean bloodAvailable(String bloodType){
        List<BloodInventory> count=bloodRepository.findBloodByType(bloodType);
        if (!count.isEmpty()) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
