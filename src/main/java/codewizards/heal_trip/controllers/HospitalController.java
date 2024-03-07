package codewizards.heal_trip.controllers;

import codewizards.heal_trip.business.HospitalService;
import codewizards.heal_trip.entities.Doctor;
import codewizards.heal_trip.entities.Hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hospital")
public class HospitalController {
    private HospitalService hospitalService;

    @Autowired
    public HospitalController(HospitalService hospitalService) {
        super();
        this.hospitalService = hospitalService;
    }

    @GetMapping("/getHospitalById/{hospital_id}")
    public ResponseEntity<Hospital> getHospitalById(@PathVariable int hospital_id) {
        Hospital hospital = hospitalService.getHospitalById(hospital_id);
        if (hospital == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        else
            return new ResponseEntity<>(hospital, HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<Integer> registerHospital(@RequestBody Hospital hospital) {
        return new ResponseEntity<>(hospitalService.registerHospital(hospital), HttpStatus.OK);
    }
}
