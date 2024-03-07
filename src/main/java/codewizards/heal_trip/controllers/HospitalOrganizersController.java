package codewizards.heal_trip.controllers;

import codewizards.heal_trip.business.IHospitalOrganizerService;
import codewizards.heal_trip.entities.HospitalOrganizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospitalOrganizer")
public class HospitalOrganizersController {

    IHospitalOrganizerService hospitalOrganizerService;

    @Autowired
    public HospitalOrganizersController(IHospitalOrganizerService hospitalOrganizerService) {
        super();
        this.hospitalOrganizerService = hospitalOrganizerService;
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
    public void add(@RequestBody HospitalOrganizer hospitalOrganizer) {
        this.hospitalOrganizerService.add(hospitalOrganizer);
    }

    @GetMapping("/get")
    public HospitalOrganizer getById(@RequestParam int id) {
        return this.hospitalOrganizerService.getById(id);
    }

    @DeleteMapping("/delete")
    public void deleteById(@RequestParam int id) {
        this.hospitalOrganizerService.deleteById(id);
    }

    @PutMapping("/update")
    public void update(@RequestBody HospitalOrganizer hospitalOrganizer) {
        this.hospitalOrganizerService.update(hospitalOrganizer);
    }
}
