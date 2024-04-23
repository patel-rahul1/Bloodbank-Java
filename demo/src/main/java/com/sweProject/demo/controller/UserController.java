package com.sweProject.demo.controller;

import com.sweProject.demo.Request.AdminLoginRequest;
import com.sweProject.demo.Request.SearchRequest;
import com.sweProject.demo.model.BloodInventory;
import com.sweProject.demo.model.EmailDetails;
import com.sweProject.demo.model.User;
import com.sweProject.demo.repository.BloodRepository;
import com.sweProject.demo.repository.UserRepository;
import com.sweProject.demo.services.EmailService;
import com.sweProject.demo.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServices userServices;

    @Autowired
    private EmailService emailService;

    @Autowired
    private BloodRepository bloodRepository;

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User registeredUser = userServices.registerUser(user);
        String toEmail = user.getEmail();
        String subject = "Registration Confirmation";
        String body = "Dear " + user.getUsername() + ",\n\n"
                + "Thank you for registering with our Blood Bank community. Your willingness to help save lives through blood donation is greatly appreciated by those in need. You are now part of a powerful movement aimed at making a critical difference!\n\n"
                + "Please keep an eye on your email for upcoming blood drives and important updates. We look forward to seeing you at one of our events soon.\n\n"
                + "If you have any questions or need further assistance, please do not hesitate to contact us.\n\n"
                + "Warm regards,\n"
                + "The Blood Bank Team";

        EmailDetails emailDetails=new EmailDetails();
        emailDetails.setSubject(subject);
        emailDetails.setMsgBody(body);
        emailDetails.setRecipient(toEmail);
        emailService.sendSimpleMail(emailDetails);

        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody AdminLoginRequest loginRequest) {
        User user = userRepository.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping("/search")
    public List<BloodInventory> getBloodByGroupAndQuantity(
            @RequestBody SearchRequest searchRequest) {
        String bloodGroup= searchRequest.getBloodGroup();
        Integer bloodQuantity=searchRequest.getBloodQuantity();
        if (bloodGroup != null && bloodQuantity != null) {
            return bloodRepository.findByBloodGroupAndQuantity(bloodGroup, bloodQuantity);
        } else if (bloodGroup != null) {
            return bloodRepository.findByBloodGroup(bloodGroup);
        } else if (bloodQuantity != null) {
            return bloodRepository.findByQuantity(bloodQuantity);
        } else {
            // If no parameters are provided, return all blood inventory
            return bloodRepository.findAll();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setUsername(updatedUser.getUsername());
            user.setPassword(updatedUser.getPassword());
            user.setEmail(updatedUser.getEmail());

            userRepository.save(user); // Save the updated user
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build(); // User with the given ID not found
        }
    }



    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}
