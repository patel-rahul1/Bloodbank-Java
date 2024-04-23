package com.sweProject.demo.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "donors")
public class Donor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "donor_id")
    private Long donorId;

    @Column(name = "username")
    private String username;


    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "quantity_ml")
    private Integer quantity;

    @Column(name = "blood_group")
    private String bloodGroup;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "last_donation_date")
    private String lastDonationDate;

    // Constructors, getters, and setters

    public Donor() {
    }

    public Donor(String username, String password, String email, String fullName, String bloodGroup, String contactNumber, String address, String lastDonationDate) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.bloodGroup = bloodGroup;
        this.contactNumber = contactNumber;
        this.address = address;
        this.lastDonationDate = lastDonationDate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getDonorId() {
        return donorId;
    }

    public void setDonorId(Long donorId) {
        this.donorId = donorId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLastDonationDate() {
        return lastDonationDate;
    }

    public void setLastDonationDate(String lastDonationDate) {
        this.lastDonationDate = lastDonationDate;
    }

    @Override
    public String toString() {
        return "Donor{" +
                "donorId=" + donorId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", bloodGroup='" + bloodGroup + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", address='" + address + '\'' +
                ", lastDonationDate='" + lastDonationDate + '\'' +
                '}';
    }
}

