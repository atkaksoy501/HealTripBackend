package codewizards.heal_trip.controllers;

import codewizards.heal_trip.business.IEmailService;
import codewizards.heal_trip.business.IPatientService;
import codewizards.heal_trip.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/patient")
@CrossOrigin
public class PatientsController {

    private IPatientService patientService;
    private IEmailService emailService;

    @Autowired
    public PatientsController(IPatientService patientService, IEmailService emailService) {
        super();
        this.patientService = patientService;
        this.emailService = emailService;
    }

    @GetMapping(value = "/get/{patient_id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable int patient_id) {
        Patient patient = patientService.getPatientById(patient_id);
        if (patient == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<String> registerPatient(@RequestBody Patient patient) throws IllegalArgumentException {
        try {
            emailService.sendWelcomeEmail(patient.getEmail(), patient.getFirst_name());
            patient.setPassword(new BCryptPasswordEncoder().encode(patient.getPassword()));
            Integer patientId = patientService.registerPatient(patient);
            return new ResponseEntity<>("Patient with id " + patientId + " has been registered. Email has ben sent to " + patient.getEmail(), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/update/{patient_id}")
    public ResponseEntity<Patient> updatePatient(@RequestBody Patient patient, @PathVariable int patient_id) {
        return new ResponseEntity<>(patientService.updatePatient(patient_id, patient), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{patient_id}")
    public ResponseEntity<String> deletePatient(@PathVariable int patient_id) {
        boolean isPatientActive = patientService.deletePatient(patient_id);
        if (!isPatientActive)
            return new ResponseEntity<>("Patient with id " + patient_id + " has been deleted", HttpStatus.OK);
        else
            return new ResponseEntity<>("Patient with id " + patient_id + " does not exist", HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<Iterable<Patient>> getAllPatients() {
        return new ResponseEntity<>(patientService.getAllPatients(), HttpStatus.OK);
    }
}
