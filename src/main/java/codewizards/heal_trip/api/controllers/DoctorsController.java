package codewizards.heal_trip.api.controllers;

import codewizards.heal_trip.business.DTOs.requests.doctor.CreateDoctorRequest;
import codewizards.heal_trip.business.DTOs.requests.doctor.UpdateDoctorRequest;
import codewizards.heal_trip.business.DTOs.responses.doctor.DoctorDTOWithHospital;
import codewizards.heal_trip.business.DTOs.responses.doctor.UpdatedDoctorResponse;
import codewizards.heal_trip.business.abstracts.IDoctorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Doctor Management", description = "Doctor Management APIs")
@RestController
@RequestMapping(value="/doctor")
@CrossOrigin
@AllArgsConstructor
public class DoctorsController {
    private final IDoctorService doctorService;

    @Operation(summary = "Get Doctor by ID")
    @GetMapping(value="/get/{doctor_id}")
    public ResponseEntity<DoctorDTOWithHospital> getDoctorById(@PathVariable int doctor_id) {
        DoctorDTOWithHospital doctor = doctorService.getDoctorById(doctor_id);
        if (doctor == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        else
            return new ResponseEntity<>(doctor, HttpStatus.OK);
    }

    @Operation(summary = "Register new Doctor")
    @PostMapping(value = "/add")
    public ResponseEntity<DoctorDTOWithHospital> registerDoctor(@Valid @RequestBody CreateDoctorRequest doctor) {
        return new ResponseEntity<>(doctorService.registerDoctor(doctor), HttpStatus.OK);
    }

    @Operation(summary = "Delete Doctor by ID")
    @DeleteMapping(value = "/delete/{doctor_id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable int doctor_id) {
        boolean isDoctorActive = doctorService.deleteDoctor(doctor_id);
        if (!isDoctorActive)
            return new ResponseEntity<>("Doctor with id " + doctor_id + " has been deleted", HttpStatus.OK);
        else
            return new ResponseEntity<>("Doctor with id " + doctor_id + " does not exist", HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Update Doctor by ID")
    @PutMapping("/update/{id}")
    public UpdatedDoctorResponse updateDoctor(@Valid @RequestBody UpdateDoctorRequest newDoctor, @PathVariable int id){
        return new ResponseEntity<>(doctorService.updateDoctor(newDoctor, id), HttpStatus.OK).getBody();
    }

    @Operation(summary = "Get all Doctors")
    @GetMapping("/getAll")
    public ResponseEntity<List<DoctorDTOWithHospital>> getAllDoctors() {
        return new ResponseEntity<>(doctorService.getAllDoctors(), HttpStatus.OK);
    }
}