package codewizards.heal_trip.api.controllers;

import java.util.*;

import codewizards.heal_trip.business.DTOs.requests.department.AddDepartmentRequest;
import codewizards.heal_trip.business.DTOs.requests.department.UpdateDepartmentRequest;
import codewizards.heal_trip.business.DTOs.responses.department.DepartmentDTO;
import codewizards.heal_trip.business.abstracts.IDepartmentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import codewizards.heal_trip.entities.Department;

@RestController
@RequestMapping("/department")
@CrossOrigin
@AllArgsConstructor
public class DepartmentsController {
    private final IDepartmentService departmentService;
    
    @GetMapping("/getAll")
    public List<DepartmentDTO> getAll() {
        List<Department> departments = this.departmentService.getAll();
        return departmentService.mapAllToDto(departments);
    }
    
    @PostMapping("/add")
    public DepartmentDTO add(@Valid @RequestBody AddDepartmentRequest department) {
        return this.departmentService.add(department);
    }
    
    @GetMapping("/getAllByPage")
    public List<DepartmentDTO> getAll(int pageNo, int pageSize){
        return this.departmentService.getAll(pageNo,pageSize);
    }
    
    @GetMapping("/getAllSorted")
    public List<DepartmentDTO> getAllSorted(){
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
    public DepartmentDTO update(@Valid @RequestBody UpdateDepartmentRequest department, @PathVariable int id){
        return this.departmentService.update(department, id);
    }
}
