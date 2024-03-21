package codewizards.heal_trip.api.controllers;

import java.util.*;

import codewizards.heal_trip.business.DTOs.responses.GotAllDepartmentsResponse;
import codewizards.heal_trip.business.abstracts.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import codewizards.heal_trip.entities.Department;

@RestController
@RequestMapping("/department")
@CrossOrigin
public class DepartmentsController {
    private IDepartmentService departmentService;
    @Autowired
    public DepartmentsController(IDepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    
    @GetMapping("/getAll")
    public List<GotAllDepartmentsResponse> getAll() {
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
    
    @GetMapping("/get/{id}")
    public Department getById(@PathVariable int id){
        return this.departmentService.getById(id);
    }
    
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable int id){
        this.departmentService.deleteById(id);
    }
    
    @PutMapping("/update/{id}")
    public Department update(@RequestBody Department department, @PathVariable int departmentId){
        return this.departmentService.update(department);
    }
}
