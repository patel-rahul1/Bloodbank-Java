package com.sweProject.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "blood_inventory")
@AllArgsConstructor
@NoArgsConstructor
public class BloodInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blood_id")
    private Long bloodId;

    @Column(name = "donor_id")
    private Long donorId;

    @Column(name = "blood_group")
    private String bloodGroup;

    @Column(name = "blood_quantity")
    private int bloodQuantity;

    @Column(name = "donation_date")
    private String donationDate;

    public Long getBloodId() {
        return bloodId;
    }

    public void setBloodId(Long bloodId) {
        this.bloodId = bloodId;
    }

    public Long getDonorId() {
        return donorId;
    }

    public void setDonorId(Long donorId) {
        this.donorId = donorId;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public int getBloodQuantity() {
        return bloodQuantity;
    }

    public void setBloodQuantity(int bloodQuantity) {
        this.bloodQuantity = bloodQuantity;
    }

    public String getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(String donationDate) {
        this.donationDate = donationDate;
    }
}
