package codewizards.heal_trip.business.rules;

import codewizards.heal_trip.business.messages.UserMessages;
import codewizards.heal_trip.core.utilities.exceptions.types.BusinessException;
import codewizards.heal_trip.dataAccess.PatientDao;
import codewizards.heal_trip.dataAccess.UserDao;
import codewizards.heal_trip.entities.Patient;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientBusinessRules extends UserBusinessRules{
    private final PatientDao patientDao;
    public PatientBusinessRules(UserDao userDao, PatientDao patientDao) {
        super(userDao);
        this.patientDao = patientDao;
    }

    @Override
    public void checkIfUserExistsBefore(String email) {
        Optional<Patient> patient = patientDao.findByEmail(email);
        if (patient.isPresent()) {
            throw new BusinessException(UserMessages.USER_ALREADY_EXISTS);
        }
    }

    @Override
    public void checkIfUserExists(int id) {
        Optional<Patient> patient = patientDao.findById(id);
        if (patient.isEmpty()) {
            throw new BusinessException(UserMessages.USER_NOT_FOUND);
        }
    }
}
