package codewizards.heal_trip.controllers;

import codewizards.heal_trip.business.HotelOrganizerService;
import codewizards.heal_trip.business.IHotelOrganizerService;
import codewizards.heal_trip.entities.Hotel;
import codewizards.heal_trip.entities.HotelOrganizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotelOrganizer")
public class HotelOrganizersController {

    IHotelOrganizerService iHotelOrganizerService;

    @Autowired
    public HotelOrganizersController(IHotelOrganizerService iHotelOrganizerService) {
        super();
        this.iHotelOrganizerService = iHotelOrganizerService;
    }

    @GetMapping("/getAll")
    public List<HotelOrganizer> getAll(){
        return this.iHotelOrganizerService.getAll();
    }

    @GetMapping("/getAllByPage")
    public List<HotelOrganizer> getAll(int pageNo, int pageSize){
        return this.iHotelOrganizerService.getAll(pageNo, pageSize);
    }

    @PostMapping("/add")
    public void add(@RequestBody HotelOrganizer hotelOrganizer) {
        this.iHotelOrganizerService.add(hotelOrganizer);
    }

    @GetMapping("/get")
    public HotelOrganizer getById(@RequestParam int id) {
        return this.iHotelOrganizerService.getById(id);
    }

    @DeleteMapping("/delete")
    public void deleteById(@RequestParam int id) {
        this.iHotelOrganizerService.deleteById(id);
    }
}
