package codewizards.heal_trip.business.abstracts;

import java.util.*;

import codewizards.heal_trip.business.DTOs.requests.department.AddDepartmentRequest;
import codewizards.heal_trip.business.DTOs.requests.department.UpdateDepartmentRequest;
import codewizards.heal_trip.business.DTOs.responses.department.DepartmentDTO;
import codewizards.heal_trip.entities.Department;

public interface IDepartmentService {
    List<Department> getAll();
    
    List<DepartmentDTO> getAll(int pageNo, int pageSize);
    
    List<DepartmentDTO> getAllSorted();
    
    DepartmentDTO add(AddDepartmentRequest department);
    Department getByDepartmentName(String departmentName);
    
    Department getById(int id);
    
    void deleteById(int id);
    
    DepartmentDTO update(UpdateDepartmentRequest department, int id);

    DepartmentDTO mapToDto(Department department);

    List<DepartmentDTO> mapAllToDto(List<Department> departments);
}
