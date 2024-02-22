package codewizards.heal_trip.controllers;

import codewizards.heal_trip.business.HospitalOrganizerService;
import codewizards.heal_trip.business.IHospitalOrganizerService;
import codewizards.heal_trip.entities.HospitalOrganizer;
import codewizards.heal_trip.entities.HotelOrganizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospitalOrganizer")
public class HospitalOrganizersController {

    IHospitalOrganizerService iHospitalOrganizerService;

    @Autowired
    public HospitalOrganizersController(IHospitalOrganizerService iHospitalOrganizerService) {
        super();
        this.iHospitalOrganizerService = iHospitalOrganizerService;
    }

    @GetMapping("/getAll")
    public List<HospitalOrganizer> getAll(){
        return this.iHospitalOrganizerService.getAll();
    }

    @GetMapping("/getAllByPage")
    public List<HospitalOrganizer> getAll(int pageNo, int pageSize){
        return this.iHospitalOrganizerService.getAll(pageNo, pageSize);
    }

    @PostMapping("/add")
    public void add(@RequestBody HospitalOrganizer hospitalOrganizer) {
        this.iHospitalOrganizerService.add(hospitalOrganizer);
    }

    @GetMapping("/get")
    public HospitalOrganizer getById(@RequestParam int id) {
        return this.iHospitalOrganizerService.getById(id);
    }

    @DeleteMapping("/delete")
    public void deleteById(@RequestParam int id) {
        this.iHospitalOrganizerService.deleteById(id);
    }
}
