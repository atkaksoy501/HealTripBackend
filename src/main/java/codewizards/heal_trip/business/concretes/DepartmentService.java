package codewizards.heal_trip.business.concretes;

import java.time.LocalDateTime;
import java.util.*;

import codewizards.heal_trip.business.abstracts.IDepartmentService;
import codewizards.heal_trip.core.utilities.mapping.ModelMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import codewizards.heal_trip.dataAccess.DepartmentDao;
import codewizards.heal_trip.entities.Department;

@Service
public class DepartmentService implements IDepartmentService {
    private DepartmentDao departmentDao;
    private ModelMapperService modelMapperService;
    
    @Autowired
    public DepartmentService(DepartmentDao departmentDao, ModelMapperService modelMapperService) {
        this.departmentDao = departmentDao;
        this.modelMapperService = modelMapperService;
    }
    
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
    public Department add(Department department) {
        department.setCreateDate(LocalDateTime.now());
        this.departmentDao.save(department);
        return department;
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
}
