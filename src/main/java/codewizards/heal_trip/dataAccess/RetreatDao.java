package codewizards.heal_trip.dataAccess;

import codewizards.heal_trip.entities.Retreat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RetreatDao extends JpaRepository<Retreat, Integer> {
}
