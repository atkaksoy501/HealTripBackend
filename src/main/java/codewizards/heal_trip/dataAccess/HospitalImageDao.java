package codewizards.heal_trip.dataAccess;

import codewizards.heal_trip.entities.HospitalImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalImageDao extends JpaRepository<HospitalImage, Integer> {
    HospitalImage findByHospitalId(int hospital_image_id);
}
