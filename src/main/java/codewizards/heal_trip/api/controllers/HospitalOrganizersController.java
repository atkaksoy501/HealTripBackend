package codewizards.heal_trip.api.controllers;

import codewizards.heal_trip.business.abstracts.IEmailService;
import codewizards.heal_trip.business.abstracts.IHospitalOrganizerService;
import codewizards.heal_trip.entities.HospitalOrganizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospitalOrganizer")
@CrossOrigin
public class HospitalOrganizersController {

    private IHospitalOrganizerService hospitalOrganizerService;
    private IEmailService emailService;

    @Autowired
    public HospitalOrganizersController(IHospitalOrganizerService hospitalOrganizerService, IEmailService emailService) {
        super();
        this.hospitalOrganizerService = hospitalOrganizerService;
        this.emailService = emailService;
    }

    @GetMapping("/getAll")
    public List<HospitalOrganizer> getAll(){
        return this.hospitalOrganizerService.getAll();
    }

    @GetMapping("/getAllByPage")
    public List<HospitalOrganizer> getAll(int pageNo, int pageSize){
        return this.hospitalOrganizerService.getAll(pageNo, pageSize);
    }

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody HospitalOrganizer hospitalOrganizer) {
        try {
//            emailService.sendWelcomeEmail(hospitalOrganizer.getEmail(), hospitalOrganizer.getFirst_name());
            hospitalOrganizer.setPassword(new BCryptPasswordEncoder().encode(hospitalOrganizer.getPassword()));
            Integer hospitalOrganizerId = hospitalOrganizerService.add(hospitalOrganizer);
            return new ResponseEntity<>("Hospital Organizer with id " + hospitalOrganizerId + " has been registered. Email has ben sent to " + hospitalOrganizer.getEmail(), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/{id}")
    public HospitalOrganizer getById(@PathVariable int id) {
        return this.hospitalOrganizerService.getById(id);
    }

    @DeleteMapping("/delete")
    public void deleteById(@RequestParam int id) {
        this.hospitalOrganizerService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public void update(@RequestBody HospitalOrganizer hospitalOrganizer, @PathVariable int hospitalOrganizerId) {
        this.hospitalOrganizerService.update(hospitalOrganizer);
    }
}
