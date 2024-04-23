package codewizards.heal_trip.business.rules;

import codewizards.heal_trip.business.messages.HospitalMessages;
import codewizards.heal_trip.dataAccess.HospitalDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HospitalBusinessRules {
    private final HospitalDao hospitalDao;
    private final DepartmentBusinessRules departmentBusinessRules;
    public void checkIfhospitalExists(int hospitalId) {
        if (!hospitalDao.existsById(hospitalId)) {
            throw new IllegalArgumentException(HospitalMessages.HOSPITAL_NOT_FOUND);
        }
    }

    public void checkIfHospitalsDepartmentExists(int departmentId) {
        departmentBusinessRules.checkIfDepartmentExists(departmentId);
    }
}
