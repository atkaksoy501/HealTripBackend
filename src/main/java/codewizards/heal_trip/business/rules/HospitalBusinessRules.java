package codewizards.heal_trip.business.rules;

import codewizards.heal_trip.business.messages.HospitalMessages;
import codewizards.heal_trip.core.utilities.exceptions.types.BusinessException;
import codewizards.heal_trip.dataAccess.HospitalDao;
import codewizards.heal_trip.entities.Hospital;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HospitalBusinessRules {
    private final HospitalDao hospitalDao;
    private final DepartmentBusinessRules departmentBusinessRules;
    public void checkHospitalExists(int hospitalId) {
        Hospital hospital = this.hospitalDao.findById(hospitalId).orElse(null);
        if (hospital == null || !hospital.isActive()) {
            throw new BusinessException(HospitalMessages.HOSPITAL_NOT_FOUND);
        }
    }

    public void checkIfHospitalsDepartmentExists(int departmentId) {
        departmentBusinessRules.checkIfDepartmentExists(departmentId);
    }
}
