package codewizards.heal_trip.dataAccess;

import codewizards.heal_trip.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientDao extends JpaRepository<Patient, Integer> {
}
