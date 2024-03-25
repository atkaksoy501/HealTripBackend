package codewizards.heal_trip.api.controllers;

import codewizards.heal_trip.business.DTOs.responses.GotHospitalsByDepartmentIdResponse;
import codewizards.heal_trip.business.concretes.HospitalService;
import codewizards.heal_trip.business.abstracts.IImageService;
import codewizards.heal_trip.entities.Hospital;
import codewizards.heal_trip.entities.HospitalImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/hospital")
@CrossOrigin
public class HospitalsController {
    private HospitalService hospitalService;
    private IImageService imageService;

    @Autowired
    public HospitalsController(HospitalService hospitalService, IImageService imageService) {
        super();
        this.hospitalService = hospitalService;
        this.imageService = imageService;
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
        List<HospitalImage> hospitalImages = hospital.getHospitalImages();
        if (hospitalImages != null && !hospitalImages.isEmpty()) { //todo : servis içinde yazılacak
            for (HospitalImage hospitalImage : hospitalImages) {
                hospitalImage.setHospital(hospital);
                imageService.saveHospitalImage(hospitalImage);
            }
        }
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

    @GetMapping(value="/getAll")
    public ResponseEntity<List<Hospital>> getAllHospitals() {
        List<Hospital> hospitals = hospitalService.getAllHospitals();
        if (hospitals.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        else
            return new ResponseEntity<>(hospitals, HttpStatus.OK);
    }

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