package com.sweProject.demo.controller;

import com.sweProject.demo.Request.SearchRequest;
import com.sweProject.demo.model.BloodInventory;
import com.sweProject.demo.repository.BloodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/blood")
public class BloodController {

    @Autowired
    private BloodRepository bloodRepository;
    @GetMapping
    public List<BloodInventory> getAllBlood() {
        return bloodRepository.findAll();
    }

    @GetMapping("/{id}")
    public BloodInventory getBloodById(@PathVariable Long id) {
        return bloodRepository.findById(id).orElse(null);
    }


    @PostMapping
    public BloodInventory createBlood(@RequestBody BloodInventory blood) {
        return bloodRepository.save(blood);
    }


    @DeleteMapping("/{id}")
    public void deleteBlood(@PathVariable Long id) {
        bloodRepository.deleteById(id);
    }
}
