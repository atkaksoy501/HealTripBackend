package codewizards.heal_trip.business;

public interface IEmailService {
    void sendEmail(String to, String subject, String text);

    boolean patternMatches(String emailAddress);

    void sendWelcomeEmail(String to, String firstName);

    void sendAppointmentEmail(String to);
}
