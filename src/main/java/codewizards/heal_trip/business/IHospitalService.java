package codewizards.heal_trip.business;

import codewizards.heal_trip.entities.Doctor;
import codewizards.heal_trip.entities.Hospital;

public interface IHospitalService {
    Hospital getHospitalById(int hospital_id);

    Integer registerHospital(Hospital hospital);
}
