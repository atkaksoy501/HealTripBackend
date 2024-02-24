package codewizards.heal_trip.business;

public interface IEmailService {
    void sendEmail(String to, String subject, String text);
}
