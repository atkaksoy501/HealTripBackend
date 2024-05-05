package codewizards.heal_trip.api.controllers;

import java.util.*;

import codewizards.heal_trip.business.DTOs.requests.department.AddDepartmentRequest;
import codewizards.heal_trip.business.DTOs.requests.department.UpdateDepartmentRequest;
import codewizards.heal_trip.business.DTOs.responses.department.DepartmentDTO;
import codewizards.heal_trip.business.abstracts.IDepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import codewizards.heal_trip.entities.Department;

@Tag(name = "Department Management", description = "Department Management APIs")
@RestController
@RequestMapping("/department")
@CrossOrigin
@AllArgsConstructor
public class DepartmentsController {
    private final IDepartmentService departmentService;

    @Operation(summary = "Get all Departments")
    @GetMapping("/getAll")
    public List<DepartmentDTO> getAll() {
        List<Department> departments = this.departmentService.getAll();
        return departmentService.mapAllToDto(departments);
    }

    @Operation(summary = "Create new Department")
    @PostMapping("/add")
    public DepartmentDTO add(@Valid @RequestBody AddDepartmentRequest department) {
        return this.departmentService.add(department);
    }

    @Operation(summary = "Get All Departments by Page")
    @GetMapping("/getAllByPage")
    public List<DepartmentDTO> getAll(int pageNo, int pageSize){
        return this.departmentService.getAll(pageNo,pageSize);
    }

    @Operation(summary = "Get All Departments Sorted")
    @GetMapping("/getAllSorted")
    public List<DepartmentDTO> getAllSorted(){
        return this.departmentService.getAllSorted();
    }

    @Operation(summary = "Get Department by Name")
    @GetMapping("/getByDepartmentName")
    public Department getByDepartmentName(@RequestParam String departmentName){
        return this.departmentService.getByDepartmentName(departmentName);
    }

    @Operation(summary = "Get Department by ID")
    @GetMapping("/get/{id}")
    public Department getById(@PathVariable int id){
        return this.departmentService.getById(id);
    }

    @Operation(summary = "Delete Department by ID")
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable int id){
        this.departmentService.deleteById(id);
    }

    @Operation(summary = "Update Department by ID")
    @PutMapping("/update/{id}")
    public DepartmentDTO update(@Valid @RequestBody UpdateDepartmentRequest department, @PathVariable int id){
        return this.departmentService.update(department, id);
    }
}
