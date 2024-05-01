package codewizards.heal_trip.business.rules;

import codewizards.heal_trip.business.messages.HospitalMessages;
import codewizards.heal_trip.core.utilities.exceptions.types.BusinessException;
import codewizards.heal_trip.dataAccess.HospitalDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HospitalBusinessRules {
    private final HospitalDao hospitalDao;
    private final DepartmentBusinessRules departmentBusinessRules;
    public void checkHospitalExists(int hospitalId) {
        if (!hospitalDao.existsById(hospitalId)) {
            throw new BusinessException(HospitalMessages.HOSPITAL_NOT_FOUND);
        }
    }

    public void checkIfHospitalsDepartmentExists(int departmentId) {
        departmentBusinessRules.checkIfDepartmentExists(departmentId);
    }
}
