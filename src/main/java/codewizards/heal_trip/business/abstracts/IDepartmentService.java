package codewizards.heal_trip.business.abstracts;

import java.util.*;

import codewizards.heal_trip.business.DTOs.responses.DepartmentDTO;
import codewizards.heal_trip.entities.Department;

public interface IDepartmentService {
    List<Department> getAll();
    
    List<Department> getAll(int pageNo, int pageSize);
    
    List<Department> getAllSorted();
    
    Department add(Department department);
    Department getByDepartmentName(String departmentName);
    
    Department getById(int id);
    
    void deleteById(int id);
    
    Department update(Department department);

    DepartmentDTO mapToDto(Department department);

    List<DepartmentDTO> mapAllToDto(List<Department> departments);
}
