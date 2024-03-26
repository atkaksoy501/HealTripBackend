package codewizards.heal_trip.business.abstracts;

import codewizards.heal_trip.business.DTOs.responses.hospital.GotHospitalByIdResponse;
import codewizards.heal_trip.entities.Hospital;

import java.util.List;

public interface IHospitalService {
    GotHospitalByIdResponse getHospitalById(int hospital_id);

    Hospital registerHospital(Hospital hospital);

    boolean deleteHospital(int hospital_id);

    Hospital updateHospital(Hospital hospital);

    List<Hospital> getAllHospitals();

}