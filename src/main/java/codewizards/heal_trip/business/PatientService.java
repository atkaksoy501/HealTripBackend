package codewizards.heal_trip.business;

import codewizards.heal_trip.dataAccess.PatientDTO;
import codewizards.heal_trip.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService implements IPatientService{

    private PatientDTO patientDTO;

    @Autowired
    public PatientService(PatientDTO patientDTO) {
        this.patientDTO = patientDTO;
    }

    public Patient getPatientById(int patient_id) {
        return patientDTO.findById(patient_id).orElse(null);
    }

    public Integer registerPatient(Patient patient) {
        Patient newPatient = new Patient();
        newPatient.setFirst_name(patient.getFirst_name());
        newPatient.setLast_name(patient.getLast_name());
        newPatient.setEmail(patient.getEmail());
        newPatient.setPhone_number(patient.getPhone_number());
        newPatient.setUser_password(patient.getUser_password());
        newPatient.setUser_role(patient.getUser_role());
        newPatient.setActive(true);
        newPatient.setBirth_year(patient.getBirth_year());
        newPatient.setGender(patient.getGender());
        newPatient.setPatient_height(patient.getPatient_height());
        newPatient.setPatient_weight(patient.getPatient_weight());
        newPatient = patientDTO.save(newPatient);
        return newPatient.getUser_id();
    }
}
