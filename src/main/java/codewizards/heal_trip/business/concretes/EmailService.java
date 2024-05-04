package codewizards.heal_trip.business.concretes;

import codewizards.heal_trip.business.DTOs.requests.email.SendEmailRequest;
import codewizards.heal_trip.business.abstracts.IEmailService;
import codewizards.heal_trip.entities.Address;
import codewizards.heal_trip.entities.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class EmailService implements IEmailService {

    private JavaMailSender emailSender;
    private String welcomeSubject = "Welcome to HealTrip";
    private String welcomeText = "Welcome to HealTrip, %s! \n\nWe are excited to have you on board. We are committed to providing you with the best healthcare services. We hope you have a great experience with us.";
    private String appointmentSubject = "Appointment Confirmation";
    private String appointmentText = "Hi %s, Your booking has been confirmed! \n\nWe are looking forward to seeing you on the scheduled date and time. You can find your booking details below. If you have any questions, feel free to contact us.";

    @Autowired
    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    private void sendEmail(String to, String subject, String text) {
        if (!patternMatches(to))
            throw new IllegalArgumentException("Invalid email address: " + to);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("healtrip.codewizards@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    public void sendContactEmail(SendEmailRequest sendEmailRequest) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sendEmailRequest.getEmail());
        message.setTo("healtrip.codewizards@gmail.com");
        message.setSubject("Contact Request From " + sendEmailRequest.getFirstName() + " " + sendEmailRequest.getLastName());
        message.setText("Name: " + sendEmailRequest.getFirstName() + " " + sendEmailRequest.getLastName() + "\n" +
                        "Address: " + sendEmailRequest.getAddress() + "\n" +
                        "Phone Number: " + sendEmailRequest.getPhoneNumber() + "\n" +
                        "Email: " + sendEmailRequest.getEmail() + "\n" +
                        "Message: " + sendEmailRequest.getMessage()
        );
        emailSender.send(message);
    }

    public void sendWelcomeEmail(String to, String firstName) {
        sendEmail(to, welcomeSubject, String.format(welcomeText, firstName));
    }

    public void sendAppointmentEmail(Booking booking) {
        String to = booking.getPatient().getEmail();
        String text = String.format(appointmentText, booking.getPatient().getFirst_name());
        text += "\n\n";
        text += "Booking Details: \n";
        text += "Patient Name: " + booking.getPatient().getFirst_name() + " " + booking.getPatient().getLast_name() + "\n";
        text += "Booking Date: " + booking.getBooking_date() + "\n";
        text += "Start Date: " + booking.getStartDate() + "\n";
        text += "End Date: " + booking.getEndDate() + "\n";
//        text += "Doctor Name: " + booking.getDoctor().getDoctorName() + "\n";
        text += "Retreat Name: " + booking.getRetreat().getRetreat_name() + "\n";
        text += "Hospital Name: " + booking.getHospital().getHospitalName() + "\n";
        Address address = booking.getHospital().getAddress();
        text += "Hospital Address: " + address.getAddressDetail() + "\n";
        if (booking.getHotel() != null) {
            text += "Hotel Name: " + booking.getHotel().getHotelName() + "\n";
            address = booking.getHotel().getAddress();
            text += "Hotel Address: " + address.getAddressDetail() + "\n";
        }
        sendEmail(to, appointmentSubject, text);
    }

    public boolean patternMatches(String emailAddress) {
        String regexPattern = "^[a-zA-Z0-9_!#$%&*+/=?{}~^.-]+@[a-zA-Z0-9.-]+$";
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }
}