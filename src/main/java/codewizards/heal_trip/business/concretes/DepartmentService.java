package codewizards.heal_trip.business.concretes;

import java.time.LocalDateTime;
import java.util.*;

import codewizards.heal_trip.business.DTOs.converters.DepartmentDbDtoConverter;
import codewizards.heal_trip.business.DTOs.requests.department.AddDepartmentRequest;
import codewizards.heal_trip.business.DTOs.requests.department.UpdateDepartmentRequest;
import codewizards.heal_trip.business.DTOs.responses.department.DepartmentDTO;
import codewizards.heal_trip.business.abstracts.IDepartmentService;
import codewizards.heal_trip.business.rules.DepartmentBusinessRules;
import codewizards.heal_trip.core.utilities.mapping.ModelMapperService;
import codewizards.heal_trip.entities.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import codewizards.heal_trip.dataAccess.DepartmentDao;

@Service
@AllArgsConstructor
public class DepartmentService implements IDepartmentService {
    private final DepartmentDao departmentDao;
    private final ModelMapperService modelMapperService;
    private final DepartmentDbDtoConverter departmentDbDtoConverter;
    private final DepartmentBusinessRules departmentBusinessRules;

    @Override
    public List<Department> getAll() {
        List<Department> departments = this.departmentDao.findAll();
        List<Department> response = new ArrayList<>();
        for (Department department : departments) {
            Department gotAllDepartmentsResponse = this.modelMapperService.forResponse().map(department, Department.class);
            response.add(gotAllDepartmentsResponse);
        }
        return response;
    }
    
    @Override
    public List<DepartmentDTO> getAll(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1, pageSize);
        List<Department> departments = this.departmentDao.findAll(pageable).getContent();
        return departments.stream().map(department -> this.modelMapperService.forResponse().map(department, DepartmentDTO.class)).toList();
    }
    
    @Override
    public List<DepartmentDTO> getAllSorted() {
        Sort sort = Sort.by(Sort.Direction.ASC, "departmentName");
        List<Department> departments = this.departmentDao.findAll(sort);
        return departments.stream().map(department -> this.modelMapperService.forResponse().map(department, DepartmentDTO.class)).toList();
    }
    
    @Override
    public DepartmentDTO add(AddDepartmentRequest department) {
        Department dbDepartment = departmentDbDtoConverter.toDbObj(department);
        dbDepartment.setCreateDate(LocalDateTime.now());
        dbDepartment = this.departmentDao.save(dbDepartment);
        return modelMapperService.forResponse().map(dbDepartment, DepartmentDTO.class);
    }
    
    @Override
    public Department getByDepartmentName(String departmentName) {
        departmentBusinessRules.checkIfDepartmentExists(departmentName);
        return this.departmentDao.getByDepartmentName(departmentName);
    }
    
    @Override
    public Department getById(int id) {
        departmentBusinessRules.checkIfDepartmentExists(id);
        return this.departmentDao.findById(id).orElse(null);
    }
    
    @Override
    public void deleteById(int id) {
        departmentBusinessRules.checkIfDepartmentExists(id);
        this.departmentDao.deleteById(id);
    }
    
    @Override
    public DepartmentDTO update(UpdateDepartmentRequest department, int id) {
        departmentBusinessRules.checkIfDepartmentExists(id);
        Department dbDepartment = getById(id);
        departmentDbDtoConverter.toDbObj(dbDepartment, department);
        return modelMapperService.forResponse().map(dbDepartment, DepartmentDTO.class);
    }

    @Override
    public DepartmentDTO mapToDto(Department department) {
        return this.modelMapperService.forResponse().map(department, DepartmentDTO.class);
    }

    @Override
    public List<DepartmentDTO> mapAllToDto(List<Department> departments) {
        List<DepartmentDTO> departmentDTOs = new ArrayList<>();
        for (Department department : departments) {
            DepartmentDTO departmentDTO = this.modelMapperService.forResponse().map(department, DepartmentDTO.class);
            departmentDTOs.add(departmentDTO);
        }
        return departmentDTOs;
    }
}
