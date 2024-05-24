package codewizards.heal_trip.api.controllers;

import codewizards.heal_trip.business.abstracts.IHotelOrganizerService;
import codewizards.heal_trip.entities.HotelOrganizer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Hotel Organizer Management", description = "Hotel Organizer Management APIs")
@RestController
@RequestMapping("/hotelOrganizer")
@CrossOrigin
@AllArgsConstructor
public class HotelOrganizersController {

    private final IHotelOrganizerService hotelOrganizerService;

    @Operation(summary = "Get all Hotel Organizers")
    @GetMapping("/getAll")
    public List<HotelOrganizer> getAll(){
        return this.hotelOrganizerService.getAll();
    }

    @Operation(summary = "Get all Hotel Organizers by Page")
    @GetMapping("/getAllByPage")
    public List<HotelOrganizer> getAll(int pageNo, int pageSize){
        return this.hotelOrganizerService.getAll(pageNo, pageSize);
    }

    @Operation(summary = "Add new Hotel Organizer")
    @PostMapping("/add")
    public ResponseEntity<String> add(@Valid @RequestBody HotelOrganizer hotelOrganizer) {
        Integer hotelOrganizerId = hotelOrganizerService.add(hotelOrganizer);
        return new ResponseEntity<>("Hotel Organizer with id " + hotelOrganizerId + " has been registered. Email has ben sent to " + hotelOrganizer.getEmail(), HttpStatus.OK);
    }

    @Operation(summary = "Get Hotel Organizer by ID")
    @GetMapping("/get/{id}")
    public HotelOrganizer getById(@PathVariable int id) {
        return this.hotelOrganizerService.getById(id);
    }

    @Operation(summary = "Delete Hotel Organizer by ID")
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable int id) {
        this.hotelOrganizerService.deleteById(id);
    }

    @Operation(summary = "Update Hotel Organizer by ID")
    @PutMapping("/update/{id}")
    public void update(@Valid @RequestBody HotelOrganizer hotelOrganizer, @PathVariable int id) {
        this.hotelOrganizerService.update(hotelOrganizer);
    }
}
