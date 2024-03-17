package codewizards.heal_trip.controllers;

import codewizards.heal_trip.business.DoctorService;
import codewizards.heal_trip.entities.Doctor;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/doctor")
@CrossOrigin
public class DoctorsController {
    private DoctorService doctorService;
    @Autowired
    public DoctorsController(DoctorService doctorService) {
        super();
        this.doctorService = doctorService;
    }

    @GetMapping(value="/get/{doctor_id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable int doctor_id) {
        Doctor doctor = doctorService.getDoctorById(doctor_id);
        if (doctor == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        else
            return new ResponseEntity<>(doctor, HttpStatus.OK);}
    @PostMapping(value = "/add")
    public ResponseEntity<Doctor> registerDoctor(@RequestBody Doctor doctor) {
        return new ResponseEntity<>(doctorService.registerDoctor(doctor), HttpStatus.OK);
    }
    @DeleteMapping(value = "/delete/{doctor_id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable int doctor_id) {
        boolean isDoctorActive = doctorService.deleteDoctor(doctor_id);
        if (!isDoctorActive)
            return new ResponseEntity<>("Doctor with id " + doctor_id + " has been deleted", HttpStatus.OK);
        else
            return new ResponseEntity<>("Doctor with id " + doctor_id + " does not exist", HttpStatus.NOT_FOUND);
    }
    @PutMapping("/update/{doctor_id}")
    public Doctor updateDoctor(@RequestBody Doctor newDoctor, @PathVariable int doctorId){
        return new ResponseEntity<>(doctorService.updateDoctor(newDoctor), HttpStatus.OK).getBody();
    }
}