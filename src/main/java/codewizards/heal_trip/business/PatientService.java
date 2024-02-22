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
        patient.setUser_role("patient");
        patient.setActive(true);
        patient = patientDTO.save(patient);
        return patient.getId();
    }

    public Patient updatePatient(int patient_id, Patient patient) {
        Patient dbPatient = patientDTO.findById(patient_id).orElse(null);
        if (dbPatient != null) {
            if (patient.getFirst_name() != null)
                dbPatient.setFirst_name(patient.getFirst_name());
            if (patient.getLast_name() != null)
                dbPatient.setLast_name(patient.getLast_name());
            if (patient.getEmail() != null)
                dbPatient.setEmail(patient.getEmail());
            if (patient.getPhone_number() != null)
                dbPatient.setPhone_number(patient.getPhone_number());
            if (patient.getUser_password() != null)
                dbPatient.setUser_password(patient.getUser_password());
            if (patient.getBirth_date() != null)
                dbPatient.setBirth_date(patient.getBirth_date());
            if (patient.getGender() != 0)
                dbPatient.setGender(patient.getGender());
            if (patient.getPatient_height() != 0)
                dbPatient.setPatient_height(patient.getPatient_height());
            if (patient.getPatient_weight() != 0)
                dbPatient.setPatient_weight(patient.getPatient_weight());
            dbPatient = patientDTO.save(dbPatient);
        }
        return dbPatient;
    }

    public boolean deletePatient(int patient_id) {
        Patient dbPatient = patientDTO.findById(patient_id).orElse(null);
        if (dbPatient != null) {
            dbPatient.setActive(false);
            patientDTO.save(dbPatient);
            return false;
        }
        return true;
    }
}
