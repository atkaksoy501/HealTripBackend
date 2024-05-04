package codewizards.heal_trip.business.abstracts;

import codewizards.heal_trip.business.DTOs.requests.email.SendEmailRequest;
import codewizards.heal_trip.entities.Booking;

public interface IEmailService {
    void sendContactEmail(SendEmailRequest sendEmailRequest);

    boolean patternMatches(String emailAddress);

    void sendWelcomeEmail(String to, String firstName);

    void sendAppointmentEmail(Booking booking);
}
