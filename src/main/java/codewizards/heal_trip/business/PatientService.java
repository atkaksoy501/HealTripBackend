package codewizards.heal_trip.business;

import codewizards.heal_trip.dataAccess.PatientDao;
import codewizards.heal_trip.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService implements IPatientService{

    private PatientDao patientDao;

    @Autowired
    public PatientService(PatientDao patientDao) {
        this.patientDao = patientDao;
    }

    public Patient getPatientById(int patient_id) {
        return patientDao.findById(patient_id).orElse(null);
    }

    public Integer registerPatient(Patient patient) {
        patient.setUser_role("patient");
        patient.setActive(true);
        patient = patientDao.save(patient);
        return patient.getId();
    }

    public Patient updatePatient(int patient_id, Patient patient) {
        Patient dbPatient = patientDao.findById(patient_id).orElse(null);
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
            dbPatient = patientDao.save(dbPatient);
        }
        return dbPatient;
    }

    public boolean deletePatient(int patient_id) {
        Patient dbPatient = patientDao.findById(patient_id).orElse(null);
        if (dbPatient != null) {
            dbPatient.setActive(false);
            patientDao.save(dbPatient);
            return false;
        }
        return true;
    }

    public Iterable<Patient> getAllPatients() {
        return patientDao.findAll();
    }
}
