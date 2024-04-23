package com.sweProject.demo.controller;

import com.sweProject.demo.Request.AdminLoginRequest;
import com.sweProject.demo.model.Admin;
import com.sweProject.demo.model.Donor;
import com.sweProject.demo.model.User;
import com.sweProject.demo.repository.AdminRepository;
import com.sweProject.demo.repository.AppointmentRepository;
import com.sweProject.demo.repository.DonorRepository;
import com.sweProject.demo.repository.UserRepository;
import com.sweProject.demo.services.AdminServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/admin")
public class AdminController {


    @Autowired
    AdminRepository adminRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DonorRepository donorRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    AdminServices adminServices;

    @PostMapping("/register")
    public ResponseEntity<Admin> registerAdmin(@RequestBody Admin admin) {
        Admin registeredAdmin = adminServices.registerAdmin(admin);
        return new ResponseEntity<>(registeredAdmin, HttpStatus.CREATED);
    }

    @DeleteMapping("/appointments/{id}")
    public ResponseEntity<Void> deleteAppointmentById(@PathVariable Long id) {
        if (!appointmentRepository.existsById(id)) {
            // If appointment with the given ID doesn't exist, return 404 Not Found
            return ResponseEntity.notFound().build();
        }
        // Delete the appointment by ID
        appointmentRepository.deleteById(id);
        // Return 204 No Content on successful deletion
        return ResponseEntity.noContent().build();
    }




    @PostMapping("/login")
    public ResponseEntity<Admin> login(@RequestBody AdminLoginRequest loginRequest) {
        Admin admin = adminServices.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
        if (admin != null) {
            return ResponseEntity.ok(admin);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

}
