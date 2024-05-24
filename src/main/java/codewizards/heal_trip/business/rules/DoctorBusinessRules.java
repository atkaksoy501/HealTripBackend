package codewizards.heal_trip.business.rules;

import codewizards.heal_trip.business.messages.DoctorMessages;
import codewizards.heal_trip.dataAccess.DoctorDao;
import codewizards.heal_trip.entities.Doctor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DoctorBusinessRules {
    private final DoctorDao doctorDao;

    public void checkIfDoctorExists(int doctorId) {
        Doctor doctor = this.doctorDao.findById(doctorId).orElse(null);
        if (doctor == null || !doctor.isActive()) {
            throw new IllegalArgumentException(DoctorMessages.DOCTOR_NOT_FOUND);
        }
    }
}
