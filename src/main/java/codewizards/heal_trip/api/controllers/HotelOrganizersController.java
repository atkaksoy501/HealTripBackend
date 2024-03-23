package codewizards.heal_trip.api.controllers;

import codewizards.heal_trip.business.abstracts.IEmailService;
import codewizards.heal_trip.business.abstracts.IHotelOrganizerService;
import codewizards.heal_trip.entities.HotelOrganizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotelOrganizer")
@CrossOrigin
public class HotelOrganizersController {

    private IHotelOrganizerService hotelOrganizerService;
    private IEmailService emailService;

    @Autowired
    public HotelOrganizersController(IHotelOrganizerService hotelOrganizerService, IEmailService emailService) {
        super();
        this.hotelOrganizerService = hotelOrganizerService;
        this.emailService = emailService;
    }

    @GetMapping("/getAll")
    public List<HotelOrganizer> getAll(){
        return this.hotelOrganizerService.getAll();
    }

    @GetMapping("/getAllByPage")
    public List<HotelOrganizer> getAll(int pageNo, int pageSize){
        return this.hotelOrganizerService.getAll(pageNo, pageSize);
    }

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody HotelOrganizer hotelOrganizer) {
        try {
//            emailService.sendWelcomeEmail(hotelOrganizer.getEmail(), hotelOrganizer.getFirst_name());
            hotelOrganizer.setPassword(new BCryptPasswordEncoder().encode(hotelOrganizer.getPassword()));
            Integer hotelOrganizerId = hotelOrganizerService.add(hotelOrganizer);
            return new ResponseEntity<>("Hotel Organizer with id " + hotelOrganizerId + " has been registered. Email has ben sent to " + hotelOrganizer.getEmail(), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/{id}")
    public HotelOrganizer getById(@PathVariable int id) {
        return this.hotelOrganizerService.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable int id) {
        this.hotelOrganizerService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public void update(@RequestBody HotelOrganizer hotelOrganizer, @PathVariable int hotelOrganizerId) {
        this.hotelOrganizerService.update(hotelOrganizer);
    }
}
