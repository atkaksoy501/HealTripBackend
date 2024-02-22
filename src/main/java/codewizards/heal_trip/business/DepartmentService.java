package codewizards.heal_trip.business;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import codewizards.heal_trip.dataAccess.DepartmentDao;
import codewizards.heal_trip.entities.Department;

@Service
public class DepartmentService implements IDepartmentService{
    private DepartmentDao departmentDao;
    
    @Autowired
    public DepartmentService(DepartmentDao departmentDao){
        this.departmentDao = departmentDao;
    }
    
    @Override
    public List<Department> getAll() {
        return this.departmentDao.findAll();
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
        this.departmentDao.save(department);
        return department;
    }
    
    @Override
    public Department getByDepartmentName(String departmentName) {
        return this.departmentDao.getByDepartmentName(departmentName);
    }
    
    @Override
    public Optional<Department> getById(int id) {
        return this.departmentDao.findById(id);
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
