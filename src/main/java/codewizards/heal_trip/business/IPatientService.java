package codewizards.heal_trip.business;

import codewizards.heal_trip.entities.Patient;

public interface IPatientService {

    Patient getPatientById(int patient_id);
}
