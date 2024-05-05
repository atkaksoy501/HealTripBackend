package codewizards.heal_trip.api.controllers;

import codewizards.heal_trip.business.DTOs.requests.hospital.AddHospitalRequest;
import codewizards.heal_trip.business.DTOs.requests.hospital.UpdateHospitalRequest;
import codewizards.heal_trip.business.DTOs.responses.hospital.AddedHospitalResponse;
import codewizards.heal_trip.business.DTOs.responses.hospital.GotHospitalByIdResponse;
import codewizards.heal_trip.business.DTOs.responses.hospital.GotHospitalsByDepartmentIdResponse;
import codewizards.heal_trip.business.DTOs.responses.hospital.UpdatedHospitalResponse;
import codewizards.heal_trip.business.abstracts.IHospitalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Hospital Management", description = "Hospital Management APIs")
@RestController
@RequestMapping(value="/hospital")
@CrossOrigin
@AllArgsConstructor
public class HospitalsController {
    private final IHospitalService hospitalService;


    @Operation(summary = "Get Hospital by ID")
    @GetMapping(value="/get/{hospital_id}")
    public ResponseEntity<GotHospitalByIdResponse> getHospitalById(@PathVariable int hospital_id) {
        GotHospitalByIdResponse hospital = hospitalService.getHospitalById(hospital_id);
        if (hospital == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        else
            return new ResponseEntity<>(hospital, HttpStatus.OK);
    }

    @Operation(summary = "Add new Hospital")
    @PostMapping(value = "/add")
    public ResponseEntity<AddedHospitalResponse> registerHospital(@Valid @RequestBody AddHospitalRequest hospital) {
        return new ResponseEntity<>(hospitalService.registerHospital(hospital), HttpStatus.CREATED);
    }

    @Operation(summary = "Delete Hospital by ID")
    @DeleteMapping(value = "/delete/{hospital_id}")
    public ResponseEntity<Boolean> deleteHospital(@PathVariable int hospital_id) {
        boolean isHospitalActive = hospitalService.deleteHospital(hospital_id);
        return new ResponseEntity<>(isHospitalActive, HttpStatus.OK);
    }

    @Operation(summary = "Update Hospital by ID")
    @PutMapping("/update/{id}")
    public UpdatedHospitalResponse updateHospital(@Valid @RequestBody UpdateHospitalRequest newHospital, @PathVariable int id){
        return new ResponseEntity<>(hospitalService.updateHospital(newHospital, id), HttpStatus.OK).getBody();
    }

    @Operation(summary = "Get all Hospitals")
    @GetMapping(value="/getAll")
    public ResponseEntity<List<GotHospitalByIdResponse>> getAllHospitals() {
        List<GotHospitalByIdResponse> hospitals = hospitalService.getAllHospitals();
        if (hospitals.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        else
            return new ResponseEntity<>(hospitals, HttpStatus.OK);
    }

    @Operation(summary = "Get Hospitals by Department ID")
    @GetMapping(value = "/getByDepartmentId/{department_id}")
    public ResponseEntity<List<GotHospitalsByDepartmentIdResponse>> getHospitalsByDepartmentId(@PathVariable int department_id) {
        List<GotHospitalsByDepartmentIdResponse> hospitals = hospitalService.getAllHospitalsByDepartmentId(department_id);
        if (hospitals.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        else
            return new ResponseEntity<>(hospitals, HttpStatus.OK);
    }
}