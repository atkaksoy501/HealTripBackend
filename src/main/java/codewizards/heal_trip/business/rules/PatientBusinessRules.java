package codewizards.heal_trip.business.rules;

import codewizards.heal_trip.business.messages.UserMessages;
import codewizards.heal_trip.core.utilities.exceptions.types.BusinessException;
import codewizards.heal_trip.dataAccess.PatientDao;
import codewizards.heal_trip.dataAccess.UserDao;
import codewizards.heal_trip.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientBusinessRules extends UserBusinessRules{
    private final PatientDao patientDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PatientBusinessRules(UserDao userDao, PatientDao patientDao, PasswordEncoder passwordEncoder) {
        super(userDao);
        this.patientDao = patientDao;
        this.passwordEncoder = passwordEncoder;
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
        if (!patientDao.existsById(id)) {
            throw new BusinessException(UserMessages.USER_NOT_FOUND);
        }
    }

    @Override
    public void checkIfOldPasswordIsCorrect(int id, String oldPassword) {
        Optional<Patient> patient = patientDao.findById(id);
        if (patient.isEmpty() || !passwordEncoder.matches(oldPassword, patient.get().getPassword())) {
            throw new BusinessException(UserMessages.OLD_PASSWORD_IS_INCORRECT);
        }
    }
}
