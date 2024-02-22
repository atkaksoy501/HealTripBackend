package codewizards.heal_trip.dataAccess;

import codewizards.heal_trip.entities.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackDao extends JpaRepository<Feedback, Integer>{
}
