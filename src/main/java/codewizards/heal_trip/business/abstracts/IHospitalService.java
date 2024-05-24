package codewizards.heal_trip.business.abstracts;

import codewizards.heal_trip.business.DTOs.requests.hospital.AddHospitalRequest;
import codewizards.heal_trip.business.DTOs.requests.hospital.UpdateHospitalRequest;
import codewizards.heal_trip.business.DTOs.responses.hospital.AddedHospitalResponse;
import codewizards.heal_trip.business.DTOs.responses.hospital.GotHospitalByIdResponse;
import codewizards.heal_trip.business.DTOs.responses.hospital.GotHospitalsByDepartmentIdResponse;
import codewizards.heal_trip.business.DTOs.responses.hospital.UpdatedHospitalResponse;
import codewizards.heal_trip.entities.Hospital;

import java.util.List;

public interface IHospitalService {
    GotHospitalByIdResponse getHospitalById(int hospital_id);

    AddedHospitalResponse registerHospital(AddHospitalRequest hospital);

    boolean deleteHospital(int hospital_id);

    UpdatedHospitalResponse updateHospital(UpdateHospitalRequest hospital, int id);

    List<GotHospitalByIdResponse> getAllHospitals();

    List<GotHospitalsByDepartmentIdResponse> getAllHospitalsByDepartmentId(int departmentId);

    void addHospital(Hospital hospital);

    Long getHospitalCount();

    List<String> getAllHospitalsDescriptions();

    List<String> getAllHospitalsLongDescriptions();

}