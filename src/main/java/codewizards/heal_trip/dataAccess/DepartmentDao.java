package codewizards.heal_trip.dataAccess;

import org.springframework.data.jpa.repository.*;
import codewizards.heal_trip.entities.Department;

public interface DepartmentDao extends JpaRepository<Department, Integer> {
    Department getByDepartmentName(String departmentName);
    
}
