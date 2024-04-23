package com.sweProject.demo.controller;

import com.sweProject.demo.Request.AdminLoginRequest;
import com.sweProject.demo.model.Donor;
import com.sweProject.demo.model.User;
import com.sweProject.demo.repository.DonorRepository;
import com.sweProject.demo.services.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/donors")
public class DonorController {

    @Autowired
    private DonorRepository donorRepository;

    @Autowired
    private DonorService donorService;

    // API endpoint to register a new donor
    @PostMapping("/register")
    public ResponseEntity<Donor> registerDonor(@RequestBody Donor donor) {
        Donor registeredDonor = donorService.registerDonor(donor);
        return new ResponseEntity<>(registeredDonor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Donor> updateDonor(@PathVariable Long id, @RequestBody Donor donorUpdate) {
        Optional<Donor> donorOptional = donorRepository.findById(id);
        if (donorOptional.isPresent()) {
            Donor existingDonor = donorOptional.get();
            existingDonor.setUsername(donorUpdate.getUsername());
            existingDonor.setPassword(donorUpdate.getPassword());
            existingDonor.setEmail(donorUpdate.getEmail());
            existingDonor.setFullName(donorUpdate.getFullName());
            existingDonor.setQuantity(donorUpdate.getQuantity());
            existingDonor.setBloodGroup(donorUpdate.getBloodGroup());
            existingDonor.setContactNumber(donorUpdate.getContactNumber());
            existingDonor.setAddress(donorUpdate.getAddress());
            existingDonor.setLastDonationDate(donorUpdate.getLastDonationDate());

            Donor updatedDonor = donorRepository.save(existingDonor);
            return new ResponseEntity<>(updatedDonor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Donor> login(@RequestBody AdminLoginRequest loginRequest) {
        Donor donor = donorRepository.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
        if (donor != null) {
            return ResponseEntity.ok(donor);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    // Endpoint to get all donors
    @GetMapping
    public ResponseEntity<List<Donor>> getAllDonors() {
        List<Donor> donors = donorRepository.findAll();
        return new ResponseEntity<>(donors, HttpStatus.OK);
    }

    // Endpoint to get a donor by ID
    @GetMapping("/{id}")
    public ResponseEntity<Donor> getDonorById(@PathVariable Long id) {
        Optional<Donor> donorOptional = donorRepository.findById(id);
        return donorOptional.map(donor -> new ResponseEntity<>(donor, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint to create a new donor
    @PostMapping
    public ResponseEntity<Donor> createDonor(@RequestBody Donor donor) {
        Donor savedDonor = donorRepository.save(donor);
        return new ResponseEntity<>(savedDonor, HttpStatus.CREATED);
    }


    // Endpoint to delete a donor by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDonor(@PathVariable Long id) {
        if (!donorRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        donorRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
