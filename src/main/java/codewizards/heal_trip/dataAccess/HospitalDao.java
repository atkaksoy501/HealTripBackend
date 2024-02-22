package codewizards.heal_trip.dataAccess;

import codewizards.heal_trip.entities.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
public interface HospitalDao extends JpaRepository<Hospital, Integer>{
}
