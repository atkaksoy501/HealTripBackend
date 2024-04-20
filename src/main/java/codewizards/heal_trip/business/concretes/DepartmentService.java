package codewizards.heal_trip.business.concretes;

import java.time.LocalDateTime;
import java.util.*;

import codewizards.heal_trip.business.DTOs.converters.DepartmentDbDtoConverter;
import codewizards.heal_trip.business.DTOs.requests.department.AddDepartmentRequest;
import codewizards.heal_trip.business.DTOs.responses.department.DepartmentDTO;
import codewizards.heal_trip.business.abstracts.IDepartmentService;
import codewizards.heal_trip.core.utilities.mapping.ModelMapperService;
import codewizards.heal_trip.entities.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import codewizards.heal_trip.dataAccess.DepartmentDao;

@Service
@AllArgsConstructor
public class DepartmentService implements IDepartmentService {
    private DepartmentDao departmentDao;
    private ModelMapperService modelMapperService;
    private DepartmentDbDtoConverter departmentDbDtoConverter;

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
    public List<Department> getAll(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1, pageSize);
        return this.departmentDao.findAll(pageable).getContent();
    }
    
    @Override
    public List<Department> getAllSorted() {
        Sort sort = Sort.by(Sort.Direction.ASC, "departmentName");
        return this.departmentDao.findAll(sort);
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
        return this.departmentDao.getByDepartmentName(departmentName);
    }
    
    @Override
    public Department getById(int id) {
        return this.departmentDao.findById(id).orElse(null);
    }
    
    @Override
    public void deleteById(int id) {
        this.departmentDao.deleteById(id);
    }
    
    @Override
    public Department update(Department department) {
        return this.departmentDao.save(department);
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
