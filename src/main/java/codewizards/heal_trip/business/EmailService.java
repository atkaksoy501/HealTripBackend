package codewizards.heal_trip.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class EmailService implements IEmailService{

    private JavaMailSender emailSender;
    private String welcomeSubject = "Welcome to HealTrip";
    private String welcomeText = "Welcome to HealTrip! \n\nWe are excited to have you on board. We are committed to providing you with the best healthcare services. We hope you have a great experience with us.";
    private String appointmentSubject = "Appointment Confirmation";
    private String appointmentText = "Your appointment has been confirmed! \n\nWe are looking forward to seeing you on the scheduled date and time. If you have any questions, feel free to contact us.";

    @Autowired
    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendEmail(String to, String subject, String text) {
        if (!patternMatches(to))
            throw new IllegalArgumentException("Invalid email address: " + to);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("healtrip.codewizards@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    public void sendWelcomeEmail(String to) {
        sendEmail(to, welcomeSubject, welcomeText);
    }

    public void sendAppointmentEmail(String to) {
        sendEmail(to, appointmentSubject, appointmentText);
    }

    public boolean patternMatches(String emailAddress) {
        String regexPattern = "^[a-zA-Z0-9_!#$%&*+/=?{}~^.-]+@[a-zA-Z0-9.-]+$";
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }
}