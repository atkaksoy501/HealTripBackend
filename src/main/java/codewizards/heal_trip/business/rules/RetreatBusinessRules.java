package codewizards.heal_trip.business.rules;

import codewizards.heal_trip.business.messages.RetreatMessages;
import codewizards.heal_trip.dataAccess.RetreatDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RetreatBusinessRules {
    private final RetreatDao retreatDao;
    private final DepartmentBusinessRules departmentBusinessRules;

    public void checkIfRetreatExists(int retreatId) {
        if (!retreatDao.existsById(retreatId)) {
            throw new IllegalArgumentException(RetreatMessages.RETREAT_NOT_FOUND);
        }
    }

    public void checkIfRetreatsDepartmentExists(int departmentId) {
        departmentBusinessRules.checkIfDepartmentExists(departmentId);
    }
}
