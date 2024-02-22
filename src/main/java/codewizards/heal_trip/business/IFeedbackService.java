package codewizards.heal_trip.business;

import codewizards.heal_trip.entities.Feedback;
public interface IFeedbackService {
    Feedback getFeedbackById(int feedback_id);

    Integer addFeedback(Feedback feedback);
}
