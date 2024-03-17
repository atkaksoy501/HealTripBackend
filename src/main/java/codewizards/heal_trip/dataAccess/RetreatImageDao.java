package codewizards.heal_trip.dataAccess;

import codewizards.heal_trip.entities.RetreatImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RetreatImageDao extends JpaRepository<RetreatImage, Integer> {
}
