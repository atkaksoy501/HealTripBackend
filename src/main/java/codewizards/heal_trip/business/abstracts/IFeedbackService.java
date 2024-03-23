package codewizards.heal_trip.business.abstracts;

import codewizards.heal_trip.entities.Feedback;
public interface IFeedbackService {
    Feedback getFeedbackById(int feedback_id);

    Feedback addFeedback(Feedback feedback);

    void deleteFeedback(int feedback_id);

    Feedback updateFeedback(Feedback feedback);
}