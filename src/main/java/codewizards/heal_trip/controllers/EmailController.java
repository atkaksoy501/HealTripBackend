package codewizards.heal_trip.controllers;

import codewizards.heal_trip.business.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/email")
public class EmailController {

    private IEmailService emailService;

    @Autowired
    public EmailController(IEmailService emailService) {
        super();
        this.emailService = emailService;
    }

    @GetMapping(value = "/send")
    public ResponseEntity<String> sendEmail(String to, String subject, String text) throws IllegalArgumentException {
        try {
            emailService.sendEmail(to, subject, text);
            return new ResponseEntity<>("Email sent to " + to, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/sendWelcome")
    public ResponseEntity<String> sendWelcomeEmail(String to) throws IllegalArgumentException {
        try {
            emailService.sendWelcomeEmail(to);
            return new ResponseEntity<>("Welcome email sent to " + to, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/sendAppointment")
    public ResponseEntity<String> sendAppointmentEmail(String to) throws IllegalArgumentException {
        try {
            emailService.sendAppointmentEmail(to);
            return new ResponseEntity<>("Appointment email sent to " + to, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
