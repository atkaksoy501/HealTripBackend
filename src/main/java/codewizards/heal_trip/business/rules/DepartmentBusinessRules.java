package codewizards.heal_trip.business.rules;

import codewizards.heal_trip.business.messages.DepartmentMessages;
import codewizards.heal_trip.dataAccess.DepartmentDao;
import codewizards.heal_trip.entities.Department;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentBusinessRules {
    private final DepartmentDao departmentDao;

    public void checkIfDepartmentExists(int departmentId) {
        if (!this.departmentDao.existsById(departmentId)) {
            throw new IllegalArgumentException(DepartmentMessages.DEPARTMENT_NOT_FOUND);
        }
    }

    public void checkIfDepartmentExists(String departmentName) {
        Department department = this.departmentDao.getByDepartmentName(departmentName);
        if (department == null) {
            throw new IllegalArgumentException(DepartmentMessages.DEPARTMENT_NOT_FOUND);
        }
    }
}
