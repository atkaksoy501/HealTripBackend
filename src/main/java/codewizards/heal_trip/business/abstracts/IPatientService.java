package codewizards.heal_trip.business.abstracts;

import codewizards.heal_trip.DTO.UserDTO;
import codewizards.heal_trip.business.DTOs.requests.patient.UpdatePatientRequest;
import codewizards.heal_trip.business.DTOs.responses.patient.UpdatedPatientResponse;
import codewizards.heal_trip.entities.Patient;

public interface IPatientService {

    Patient getPatientById(int patient_id);

    Patient registerPatient(UserDTO patient);

    UpdatedPatientResponse updatePatient(int patient_id, UpdatePatientRequest patient);

    boolean deletePatient(int patient_id);

    Iterable<Patient> getAllPatients();
}
