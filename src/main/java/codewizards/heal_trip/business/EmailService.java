package codewizards.heal_trip.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailService implements IEmailService{

    private JavaMailSender emailSender;

    @Autowired
    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("healtrip.codewizards@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
}