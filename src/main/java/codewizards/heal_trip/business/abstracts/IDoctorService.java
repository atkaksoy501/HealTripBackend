package codewizards.heal_trip.business.abstracts;

import codewizards.heal_trip.business.DTOs.responses.DoctorDTOWithHospital;
import codewizards.heal_trip.entities.Doctor;

import java.util.List;

public interface IDoctorService {
    DoctorDTOWithHospital getDoctorById(int doctor_id);
    Doctor registerDoctor(Doctor doctor);
    boolean deleteDoctor(int doctor_id);
    Doctor updateDoctor(Doctor doctor);

    List<Doctor> getAllDoctors();
}