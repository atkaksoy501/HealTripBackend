package codewizards.heal_trip.business.rules;

import codewizards.heal_trip.business.messages.DoctorMessages;
import codewizards.heal_trip.dataAccess.DoctorDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DoctorBusinessRules {
    private final DoctorDao doctorDao;

    public void checkIfDoctorExists(int doctorId) {
        if (!this.doctorDao.existsById(doctorId)) {
            throw new IllegalArgumentException(DoctorMessages.DOCTOR_NOT_FOUND);
        }
    }
}
