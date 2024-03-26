package codewizards.heal_trip.business.abstracts;

import codewizards.heal_trip.business.DTOs.requests.doctor.CreateDoctorRequest;
import codewizards.heal_trip.business.DTOs.responses.doctor.DoctorDTOWithHospital;
import codewizards.heal_trip.entities.Doctor;

import java.util.List;

public interface IDoctorService {
    DoctorDTOWithHospital getDoctorById(int doctor_id);
    Doctor registerDoctor(CreateDoctorRequest doctor);
    boolean deleteDoctor(int doctor_id);
    Doctor updateDoctor(Doctor doctor);

    List<Doctor> getAllDoctors();
}