package codewizards.heal_trip.api.controllers;

import codewizards.heal_trip.business.abstracts.IHospitalOrganizerService;
import codewizards.heal_trip.entities.HospitalOrganizer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Hospital Organizer Management", description = "Hospital Organizer Management APIs")
@RestController
@RequestMapping("/hospitalOrganizer")
@CrossOrigin
@AllArgsConstructor
public class HospitalOrganizersController {

    private final IHospitalOrganizerService hospitalOrganizerService;

    @Operation(summary = "Get all Hospital Organizers")
    @GetMapping("/getAll")
    public List<HospitalOrganizer> getAll(){
        return this.hospitalOrganizerService.getAll();
    }

    @Operation(summary = "Get all Hospital Organizers by Page")
    @GetMapping("/getAllByPage")
    public List<HospitalOrganizer> getAll(int pageNo, int pageSize){
        return this.hospitalOrganizerService.getAll(pageNo, pageSize);
    }

    @Operation(summary = "Add new Hospital Organizer")
    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody HospitalOrganizer hospitalOrganizer) {
        Integer hospitalOrganizerId = hospitalOrganizerService.add(hospitalOrganizer);
        return new ResponseEntity<>("Hospital Organizer with id " + hospitalOrganizerId + " has been registered. Email has ben sent to " + hospitalOrganizer.getEmail(), HttpStatus.OK);
    }

    @Operation(summary = "Get Hospital Organizer by ID")
    @GetMapping("/get/{id}")
    public HospitalOrganizer getById(@PathVariable int id) {
        return this.hospitalOrganizerService.getById(id);
    }

    @Operation(summary = "Delete Hospital Organizer by ID")
    @DeleteMapping("/delete")
    public void deleteById(@RequestParam int id) {
        this.hospitalOrganizerService.deleteById(id);
    }

    @Operation(summary = "Update Hospital Organizer by ID")
    @PutMapping("/update/{id}")
    public void update(@RequestBody HospitalOrganizer hospitalOrganizer, @PathVariable int hospitalOrganizerId) {
        this.hospitalOrganizerService.update(hospitalOrganizer);
    }
}
