package codewizards.heal_trip.controllers;

import codewizards.heal_trip.business.HospitalService;
import codewizards.heal_trip.entities.Hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/hospital")
@CrossOrigin
public class HospitalsController {
    private HospitalService hospitalService;

    @Autowired
    public HospitalsController(HospitalService hospitalService) {
        super();
        this.hospitalService = hospitalService;
    }

    @GetMapping(value="/get/{hospital_id}")
    public ResponseEntity<Hospital> getHospitalById(@PathVariable int hospital_id) {
        Hospital hospital = hospitalService.getHospitalById(hospital_id);
        if (hospital == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        else
            return new ResponseEntity<>(hospital, HttpStatus.OK);
    }
    @PostMapping(value = "/add")
    public ResponseEntity<Hospital> registerHospital(@RequestBody Hospital hospital) {
        return new ResponseEntity<>(hospitalService.registerHospital(hospital), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{hospital_id}")
    public ResponseEntity<String> deleteHospital(@PathVariable int hospital_id) {
        boolean isHospitalActive = hospitalService.deleteHospital(hospital_id);
        if (!isHospitalActive)
            return new ResponseEntity<>("Hospital with id " + hospital_id + " has been deleted", HttpStatus.OK);
        else
            return new ResponseEntity<>("Hospital with id " + hospital_id + " does not exist", HttpStatus.NOT_FOUND);
    }
    @PutMapping("/update/{hospital_id}")
    public Hospital updateHospital(@RequestBody Hospital newHospital, @PathVariable int hospitalId){
        return new ResponseEntity<>(hospitalService.updateHospital(newHospital), HttpStatus.OK).getBody();
    }
}