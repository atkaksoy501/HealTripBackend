package codewizards.heal_trip.api.controllers;

import codewizards.heal_trip.business.DTOs.requests.doctor.CreateDoctorRequest;
import codewizards.heal_trip.business.DTOs.requests.doctor.UpdateDoctorRequest;
import codewizards.heal_trip.business.DTOs.responses.doctor.DoctorDTO;
import codewizards.heal_trip.business.DTOs.responses.doctor.DoctorDTOWithHospital;
import codewizards.heal_trip.business.DTOs.responses.doctor.UpdatedDoctorResponse;
import codewizards.heal_trip.business.abstracts.IDoctorService;
import codewizards.heal_trip.business.concretes.DoctorService;
import codewizards.heal_trip.entities.Doctor;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/doctor")
@CrossOrigin
public class DoctorsController {
    private final IDoctorService doctorService;
    @Autowired
    public DoctorsController(DoctorService doctorService) {
        super();
        this.doctorService = doctorService;
    }

    @GetMapping(value="/get/{doctor_id}")
    public ResponseEntity<DoctorDTOWithHospital> getDoctorById(@PathVariable int doctor_id) {
        DoctorDTOWithHospital doctor = doctorService.getDoctorById(doctor_id);
        if (doctor == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        else
            return new ResponseEntity<>(doctor, HttpStatus.OK);}
    @PostMapping(value = "/add")
    public ResponseEntity<DoctorDTOWithHospital> registerDoctor(@Valid @RequestBody CreateDoctorRequest doctor) {
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
    @PutMapping("/update/{id}")
    public UpdatedDoctorResponse updateDoctor(@Valid @RequestBody UpdateDoctorRequest newDoctor, @PathVariable int id){
        return new ResponseEntity<>(doctorService.updateDoctor(newDoctor, id), HttpStatus.OK).getBody();
    }
}