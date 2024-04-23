package codewizards.heal_trip.business.rules;

import codewizards.heal_trip.business.messages.FeedbackMessages;
import codewizards.heal_trip.dataAccess.FeedbackDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FeedbackBusinessRules {
    private final FeedbackDao feedbackDao;

    public void checkIfFeedbackExists(int feedbackId) {
        if (!feedbackDao.existsById(feedbackId)) {
            throw new IllegalArgumentException(FeedbackMessages.FEEDBACK_NOT_FOUND);
        }
    }
}
