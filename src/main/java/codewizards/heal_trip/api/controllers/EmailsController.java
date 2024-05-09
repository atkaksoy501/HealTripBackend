package codewizards.heal_trip.api.controllers;

import codewizards.heal_trip.business.DTOs.requests.email.SendEmailRequest;
import codewizards.heal_trip.business.abstracts.IEmailService;
import codewizards.heal_trip.entities.Booking;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Email Management", description = "Email Management APIs")
@RestController
@RequestMapping("/email")
@CrossOrigin
@AllArgsConstructor
public class EmailsController {

    private final IEmailService emailService;

    @Operation(summary = "Send Email")
    @PostMapping("/send")
    @ResponseStatus(HttpStatus.OK)
    public void sendEmail(@Valid @RequestBody SendEmailRequest sendEmailRequest) {
        emailService.sendContactEmail(sendEmailRequest);
    }

    @Operation(summary = "Send Welcome Email")
    @PostMapping("/sendWelcome")
    public ResponseEntity<String> sendWelcomeEmail(String to, String firstName) throws IllegalArgumentException {
        try {
            emailService.sendWelcomeEmail(to, firstName);
            return new ResponseEntity<>("Welcome email sent to " + to, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Send Appointment Email")
    @PostMapping("/sendAppointment")
    public ResponseEntity<String> sendAppointmentEmail(@Valid @RequestBody Booking booking) throws IllegalArgumentException {
        try {
            emailService.sendAppointmentEmail(booking);
            return new ResponseEntity<>("Appointment email sent to " + booking.getPatient().getEmail(), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
