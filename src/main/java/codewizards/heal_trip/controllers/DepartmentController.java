package codewizards.heal_trip.controllers;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import codewizards.heal_trip.business.*;
import codewizards.heal_trip.entities.Department;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private IDepartmentService departmentService;
    @Autowired
    public DepartmentController(IDepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    
    @GetMapping("/getAll")
    public List<Department> getAll() {
        return this.departmentService.getAll();
    }
    
    @PostMapping("/add")
    public Department add(@RequestBody Department department) {
        return this.departmentService.add(department);
    }
    
    @GetMapping("/getAllByPage")
    public List<Department> getAll(int pageNo, int pageSize){
        return this.departmentService.getAll(pageNo,pageSize);
    }
    
    @GetMapping("/getAllSorted")
    public List<Department> getAllSorted(){
        return this.departmentService.getAllSorted();
    }
    
    @GetMapping("/getByDepartmentName")
    public Department getByDepartmentName(@RequestParam String departmentName){
        return this.departmentService.getByDepartmentName(departmentName);
    }
    
    @GetMapping("/getById")
    public Optional<Department> getById(@RequestParam int id){
        return this.departmentService.getById(id);
    }
    
    @DeleteMapping("/deleteById")
    public void deleteById(@RequestParam int id){
        this.departmentService.deleteById(id);
    }
    
    @PutMapping("/update")
    public Department update(@RequestBody Department department){
        return this.departmentService.update(department);
    }
}
