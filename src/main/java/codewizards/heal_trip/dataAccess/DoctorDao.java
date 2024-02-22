package codewizards.heal_trip.dataAccess;

import codewizards.heal_trip.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorDao extends JpaRepository<Doctor, Integer> {
}