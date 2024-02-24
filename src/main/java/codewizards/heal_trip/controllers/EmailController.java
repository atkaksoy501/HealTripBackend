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

    private String welcomeSubject = "Welcome to HealTrip";
    private String welcomeText = "Welcome to HealTrip! We are excited to have you on board. We are committed to providing you with the best healthcare services. We hope you have a great experience with us.";
    private String appointmentSubject = "Appointment Confirmation";
    private String appointmentText = "Your appointment has been confirmed. We are looking forward to seeing you on the scheduled date and time. If you have any questions, feel free to contact us.";

    @Autowired
    public EmailController(IEmailService emailService) {
        super();
        this.emailService = emailService;
    }

    @GetMapping(value = "/send")
    public ResponseEntity<String> sendEmail(String to, String subject, String text) {
        emailService.sendEmail(to, subject, text);
        return new ResponseEntity<>("Email sent to " + to, HttpStatus.OK);
    }

    @GetMapping(value = "/sendWelcome")
    public ResponseEntity<String> sendWelcomeEmail(String to) {
        emailService.sendEmail(to, welcomeSubject, welcomeText);
        return new ResponseEntity<>("Welcome email sent to " + to, HttpStatus.OK);
    }

    @GetMapping(value = "/sendAppointment")
    public ResponseEntity<String> sendAppointmentEmail(String to) {
        emailService.sendEmail(to, appointmentSubject, appointmentText);
        return new ResponseEntity<>("Appointment email sent to " + to, HttpStatus.OK);
    }

}
