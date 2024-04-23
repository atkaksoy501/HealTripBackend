package codewizards.heal_trip.api.controllers;

import codewizards.heal_trip.business.abstracts.IEmailService;
import codewizards.heal_trip.entities.Booking;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
@CrossOrigin
@AllArgsConstructor
public class EmailsController {

    private final IEmailService emailService;

    @GetMapping("/send")
    public ResponseEntity<String> sendEmail(String to, String subject, String text) throws IllegalArgumentException {
        try {
            emailService.sendEmail(to, subject, text);
            return new ResponseEntity<>("Email sent to " + to, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/sendWelcome")
    public ResponseEntity<String> sendWelcomeEmail(String to, String firstName) throws IllegalArgumentException {
        try {
            emailService.sendWelcomeEmail(to, firstName);
            return new ResponseEntity<>("Welcome email sent to " + to, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/sendAppointment")
    public ResponseEntity<String> sendAppointmentEmail(@Valid @RequestBody Booking booking) throws IllegalArgumentException {
        try {
            emailService.sendAppointmentEmail(booking);
            return new ResponseEntity<>("Appointment email sent to " + booking.getPatient().getEmail(), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
