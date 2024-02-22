package codewizards.heal_trip.business;

import java.util.*;
import codewizards.heal_trip.entities.Department;

public interface IDepartmentService {
    List<Department> getAll();
    
    List<Department> getAll(int pageNo, int pageSize);
    
    List<Department> getAllSorted();
    
    Department add(Department department);
    Department getByDepartmentName(String departmentName);
    
    Optional<Department> getById(int id);
    
    void deleteById(int id);
    
    Department update(Department department);
}
