package com.sweProject.demo.controller;

import com.sweProject.demo.model.Appointment;
import com.sweProject.demo.model.Donor;
import com.sweProject.demo.model.EmailDetails;
import com.sweProject.demo.repository.AppointmentRepository;
import com.sweProject.demo.repository.DonorRepository;
import com.sweProject.demo.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class AppointmentController {

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    DonorRepository donorRepository;


    @GetMapping("/appointments")
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        // Retrieve all appointments from the repository
        List<Appointment> appointments = appointmentRepository.findAll();

        if (appointments.isEmpty()) {
            // If no appointments are found, return 404 Not Found
            return ResponseEntity.notFound().build();
        }

        // Return the list of appointments with 200 OK status
        return ResponseEntity.ok().body(appointments);
    }

    @GetMapping("/appointments/{donorId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByDonorId(@PathVariable Long donorId) {
        // Retrieve appointments by donor ID from the repository
        List<Appointment> appointments = appointmentRepository.findByDonorId(donorId);

        if (appointments.isEmpty()) {
            // If no appointments are found, return 404 Not Found
            return ResponseEntity.notFound().build();
        }

        // Return the list of appointments with 200 OK status
        return ResponseEntity.ok().body(appointments);
    }


    @PostMapping("/appointments/book")
    public ResponseEntity<Appointment> bookAppointment(@RequestBody Appointment appointment) {
        Donor donor=donorRepository.getById(appointment.getDonorId());

        Appointment bookedAppointment = appointmentRepository.save(appointment);
        String toEmail = donor.getEmail();
        String subject = "Appointment Confirmation";
        String body = "Dear " + donor.getFullName() + ",\n\n"
                + "We are delighted to confirm your appointment for blood donation at our Blood Bank. Your commitment to helping others by donating blood is deeply appreciated, and your contribution will make a significant impact on the lives of those in need.\n\n"
                + "Appointment Details:\n"
                + "Date: " + appointment.getAppointmentDate() + "\n"
                + "Time: " + appointment.getAppointmentTime() + "\n"
                + "Please ensure that you are well hydrated and have had a light meal before coming in. Bring a photo ID and remember to wear comfortable clothing.\n\n"
                + "Should you need to reschedule or if you have any questions, please contact us at your earliest convenience. We look forward to seeing you and thank you once again for your generosity and support.\n\n"
                + "Warm regards,\n"
                + "The Blood Bank Team";
        EmailDetails emailDetails=new EmailDetails();
        emailDetails.setSubject(subject);
        emailDetails.setMsgBody(body);
        emailDetails.setRecipient(toEmail);
        emailService.sendSimpleMail(emailDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookedAppointment);
    }

    @DeleteMapping("/appointments/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        // Check if the appointment exists
        if (!appointmentRepository.existsById(id)) {
            // If the appointment does not exist, return 404 Not Found
            return ResponseEntity.notFound().build();
        }
        // Delete the appointment
        appointmentRepository.deleteById(id);
        // Return 204 No Content to indicate successful deletion
        return ResponseEntity.noContent().build();
    }
}
