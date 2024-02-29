package codewizards.heal_trip.business;

import codewizards.heal_trip.entities.Doctor;
public interface IDoctorService {
    Doctor getDoctorById(int doctor_id);
    Doctor registerDoctor(Doctor doctor);
    boolean deleteDoctor(int doctor_id);
    Doctor updateDoctor(Doctor doctor);
}

