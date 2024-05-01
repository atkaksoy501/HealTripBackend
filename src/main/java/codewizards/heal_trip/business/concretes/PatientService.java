package codewizards.heal_trip.business.concretes;

import codewizards.heal_trip.DTO.UserDTO;
import codewizards.heal_trip.business.DTOs.requests.patient.CreatePatientRequest;
import codewizards.heal_trip.business.DTOs.requests.patient.UpdatePatientRequest;
import codewizards.heal_trip.business.DTOs.responses.patient.CreatedPatientResponse;
import codewizards.heal_trip.business.DTOs.responses.patient.GetPatientResponse;
import codewizards.heal_trip.business.DTOs.responses.patient.UpdatedPatientResponse;
import codewizards.heal_trip.business.abstracts.IEmailService;
import codewizards.heal_trip.business.abstracts.IPatientService;
import codewizards.heal_trip.business.rules.PatientBusinessRules;
import codewizards.heal_trip.core.utilities.mapping.ModelMapperService;
import codewizards.heal_trip.dataAccess.PatientDao;
import codewizards.heal_trip.entities.Booking;
import codewizards.heal_trip.entities.Patient;
import codewizards.heal_trip.entities.enums.Gender;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PatientService implements IPatientService {

    private PatientDao patientDao;
    private IEmailService emailService;
    private ModelMapperService modelMapperService;
    private PatientBusinessRules patientBusinessRules;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public GetPatientResponse getPatientById(int patient_id) {
        Patient patient = patientDao.findById(patient_id).orElse(null);
        return modelMapperService.forResponse().map(patient, GetPatientResponse.class);
    }

    public Patient getPatient(int patient_id) {
        return patientDao.findById(patient_id).orElse(null);
    }

    public Patient registerPatient(UserDTO patient) {
        patientBusinessRules.checkIfUserExistsBefore(patient.getEmail());
        Patient dbPatient = modelMapperService.forRequest().map(patient, Patient.class);
        dbPatient.setPassword(passwordEncoder.encode(patient.getPassword()));
        dbPatient.setRoles("PATIENT");
        dbPatient.setActive(true);
        dbPatient.setCreateDate(LocalDateTime.now());
        dbPatient.setGender(Gender.UNDEFINED);
        return patientDao.save(dbPatient);
    }

    public CreatedPatientResponse registerPatient(CreatePatientRequest patient) {
        patientBusinessRules.checkIfUserExistsBefore(patient.getEmail());
        Patient dbPatient = modelMapperService.forRequest().map(patient, Patient.class);
        dbPatient.setPassword(passwordEncoder.encode(patient.getPassword()));
        dbPatient.setRoles("PATIENT");
        dbPatient.setActive(true);
        dbPatient.setCreateDate(LocalDateTime.now());
        CreatedPatientResponse response = modelMapperService.forResponse().map(patientDao.save(dbPatient), CreatedPatientResponse.class);
//        emailService.sendWelcomeEmail(patient.getEmail(), patient.getFirst_name());
        return response;
    }

    public UpdatedPatientResponse updatePatient(int patient_id, UpdatePatientRequest patient) {
        patientBusinessRules.checkIfUserExists(patient_id);
        patientBusinessRules.checkIfUserExistsBefore(patient.getEmail());
        Patient dbPatient = patientDao.findById(patient_id).orElse(null);
        String newPassword = patient.getPassword();
        String oldPassword = patient.getOldPassword();
        patient.setPassword(null);
        patient.setOldPassword(null);
        modelMapperService.forUpdate().map(patient, dbPatient);
        if (newPassword != null) {
            patientBusinessRules.checkIfOldPasswordIsCorrect(patient_id, oldPassword);
            dbPatient.setPassword(passwordEncoder.encode(newPassword));
        }
        List<Booking> bookings = new ArrayList<>();
        if (patient.getBooking_ids() != null) {
            for (Integer booking_id : patient.getBooking_ids()) {
                Booking booking = new Booking();
                booking.setId(booking_id);
                bookings.add(booking);
            }
        }
        dbPatient.setBookings(bookings);
        dbPatient.setUpdateDate(LocalDateTime.now());
        Patient updatedPatient = patientDao.save(dbPatient);

        UpdatedPatientResponse response = new UpdatedPatientResponse();
        response.setId(updatedPatient.getId());
        response.setFirst_name(updatedPatient.getFirst_name());
        response.setLast_name(updatedPatient.getLast_name());
        response.setEmail(updatedPatient.getEmail());
        response.setPhone_number(updatedPatient.getPhone_number());
        response.setBirth_date(updatedPatient.getBirth_date());
        response.setGender(updatedPatient.getGender());
        response.setPatient_height(updatedPatient.getPatient_height());
        response.setPatient_weight(updatedPatient.getPatient_weight());
        response.setBooking_ids(updatedPatient.getBookings().stream().map(Booking::getId).toList());
        response.setUpdateDate(updatedPatient.getUpdateDate());

        return response;
    }

    public boolean deletePatient(int patient_id) {
        patientBusinessRules.checkIfUserExists(patient_id);
        Patient dbPatient = patientDao.findById(patient_id).orElse(null);
        if (dbPatient != null) {
            dbPatient.setActive(false);
            patientDao.save(dbPatient);
            return false;
        }
        return true;
    }

    public Iterable<GetPatientResponse> getAllPatients() {
        return patientDao.findAll().stream().map(patient -> modelMapperService.forResponse().map(patient, GetPatientResponse.class)).toList();
    }
}
