package codewizards.heal_trip.business;

import codewizards.heal_trip.entities.Patient;

public interface IPatientService {

    Patient getPatientById(int patient_id);

    Integer registerPatient(Patient patient);

    Patient updatePatient(int patient_id, Patient patient);

    boolean deletePatient(int patient_id);
}
