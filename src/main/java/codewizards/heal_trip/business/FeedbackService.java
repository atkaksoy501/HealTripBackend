package codewizards.heal_trip.business;

import codewizards.heal_trip.entities.Doctor;
import codewizards.heal_trip.entities.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import codewizards.heal_trip.dataAccess.FeedbackDao;

@Service
public class FeedbackService implements IFeedbackService{
    private FeedbackDao feedbackDao;

    @Autowired
    public FeedbackService(FeedbackDao feedbackDao) {
        this.feedbackDao = feedbackDao;
    }

    @Override
    public Feedback getFeedbackById(int feedback_id) {
        return feedbackDao.findById(feedback_id).orElse(null);
    }

    @Override
    public Feedback addFeedback(Feedback feedback) {
        return feedbackDao.save(feedback);
    }

    @Override
    public void deleteFeedback(int feedback_id) {
        feedbackDao.deleteById(feedback_id);
    }

    @Override
    public Feedback updateFeedback(Feedback feedback) {
        return feedbackDao.save(feedback);
    }
}