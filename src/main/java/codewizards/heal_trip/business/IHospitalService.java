package codewizards.heal_trip.business;

import codewizards.heal_trip.entities.Doctor;
import codewizards.heal_trip.entities.Hospital;

import java.util.List;

public interface IHospitalService {
    Hospital getHospitalById(int hospital_id);

    Hospital registerHospital(Hospital hospital);

    boolean deleteHospital(int hospital_id);

    Hospital updateHospital(Hospital hospital);

    List<Hospital> getAllHospitals();

}