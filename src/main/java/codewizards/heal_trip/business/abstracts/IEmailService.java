package codewizards.heal_trip.business.abstracts;

import codewizards.heal_trip.entities.Booking;

public interface IEmailService {
    void sendEmail(String to, String subject, String text);

    boolean patternMatches(String emailAddress);

    void sendWelcomeEmail(String to, String firstName);

    void sendAppointmentEmail(Booking booking);
}
