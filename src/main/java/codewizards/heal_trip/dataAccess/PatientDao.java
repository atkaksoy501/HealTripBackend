package codewizards.heal_trip.dataAccess;

import codewizards.heal_trip.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientDao extends JpaRepository<Patient, Integer> {
    Optional<Patient> findByEmail(String email);
}
