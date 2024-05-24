package codewizards.heal_trip.business.concretes;

import codewizards.heal_trip.business.abstracts.IFeedbackService;
import codewizards.heal_trip.business.rules.FeedbackBusinessRules;
import codewizards.heal_trip.entities.Feedback;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import codewizards.heal_trip.dataAccess.FeedbackDao;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class FeedbackService implements IFeedbackService {
    private final FeedbackDao feedbackDao;
    private final FeedbackBusinessRules feedbackBusinessRules;


    @Override
    public Feedback getFeedbackById(int feedback_id) {
        feedbackBusinessRules.checkIfFeedbackExists(feedback_id);
        return feedbackDao.findById(feedback_id).orElse(null);
    }

    @Override
    public Feedback addFeedback(Feedback feedback) {
        feedback.setCreateDate(LocalDateTime.now());
        return feedbackDao.save(feedback);
    }

    @Override
    public void deleteFeedback(int feedback_id) {
        feedbackBusinessRules.checkIfFeedbackExists(feedback_id);
        feedbackDao.deleteById(feedback_id);
    }

    @Override
    public Feedback updateFeedback(Feedback feedback) {
        feedbackBusinessRules.checkIfFeedbackExists(feedback.getId());
        return feedbackDao.save(feedback); //todo: implement
    }
}