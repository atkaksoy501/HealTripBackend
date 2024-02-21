package codewizards.heal_trip.controllers;

import codewizards.heal_trip.business.IPatientService;
import codewizards.heal_trip.business.PatientService;
import codewizards.heal_trip.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/patient")
public class PatientController {

    private IPatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        super();
        this.patientService = patientService;
    }

    @GetMapping(value = "/getById/{patient_id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable int patient_id) {
        Patient patient = patientService.getPatientById(patient_id);
        if (patient == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<Integer> registerPatient(@RequestBody Patient patient) {
        return new ResponseEntity<>(patientService.registerPatient(patient), HttpStatus.OK);
    }

    @PatchMapping(value = "/update/{patient_id}")
    public ResponseEntity<Patient> updatePatient(@RequestBody Patient patient, @PathVariable int patient_id) {
        return new ResponseEntity<>(patientService.updatePatient(patient_id, patient), HttpStatus.OK);
    }
}
