package codewizards.heal_trip.api.controllers;

import codewizards.heal_trip.business.DTOs.requests.patient.CreatePatientRequest;
import codewizards.heal_trip.business.DTOs.requests.patient.UpdatePatientRequest;
import codewizards.heal_trip.business.DTOs.responses.patient.CreatedPatientResponse;
import codewizards.heal_trip.business.DTOs.responses.patient.GetPatientResponse;
import codewizards.heal_trip.business.DTOs.responses.patient.UpdatedPatientResponse;
import codewizards.heal_trip.business.abstracts.IPatientService;
import codewizards.heal_trip.entities.Patient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Patient Management", description = "Patient Management APIs")
@RestController
@RequestMapping(value = "/patient")
@CrossOrigin
@AllArgsConstructor
public class PatientsController {

    private final IPatientService patientService;

    @Operation(summary = "Get Patient by ID")
    @GetMapping(value = "/get/{patient_id}")
    public ResponseEntity<GetPatientResponse> getPatientById(@PathVariable int patient_id) {
        GetPatientResponse patient = patientService.getPatientById(patient_id);
        if (patient == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @Operation(summary = "Register new Patient")
    @PostMapping(value = "/add")
    public ResponseEntity<CreatedPatientResponse> registerPatient(@Valid @RequestBody CreatePatientRequest patient) throws IllegalArgumentException {
        CreatedPatientResponse dbPatient = patientService.registerPatient(patient);
        return new ResponseEntity<>(dbPatient, HttpStatus.OK);
    }

    @Operation(summary = "Update Patient by ID")
    @PutMapping(value = "/update/{patient_id}")
    public ResponseEntity<UpdatedPatientResponse> updatePatient(@Valid @RequestBody UpdatePatientRequest patient, @PathVariable int patient_id) {
        return new ResponseEntity<>(patientService.updatePatient(patient_id, patient), HttpStatus.OK);
    }

    @Operation(summary = "Delete Patient by ID")
    @DeleteMapping(value = "/delete/{patient_id}")
    public ResponseEntity<String> deletePatient(@PathVariable int patient_id) {
        boolean isPatientActive = patientService.deletePatient(patient_id);
        if (!isPatientActive)
            return new ResponseEntity<>("Patient with id " + patient_id + " has been deleted", HttpStatus.OK);
        else
            return new ResponseEntity<>("Patient with id " + patient_id + " does not exist", HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Get all Patients")
    @GetMapping(value = "/getAll")
    public ResponseEntity<Iterable<GetPatientResponse>> getAllPatients() {
        return new ResponseEntity<>(patientService.getAllPatients(), HttpStatus.OK);
    }
}
