package codewizards.heal_trip.controllers;

import codewizards.heal_trip.business.IHotelOrganizerService;
import codewizards.heal_trip.entities.HotelOrganizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotelOrganizer")
public class HotelOrganizersController {

    IHotelOrganizerService hotelOrganizerService;

    @Autowired
    public HotelOrganizersController(IHotelOrganizerService hotelOrganizerService) {
        super();
        this.hotelOrganizerService = hotelOrganizerService;
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
    public void add(@RequestBody HotelOrganizer hotelOrganizer) {
        this.hotelOrganizerService.add(hotelOrganizer);
    }

    @GetMapping("/get")
    public HotelOrganizer getById(@RequestParam int id) {
        return this.hotelOrganizerService.getById(id);
    }

    @DeleteMapping("/delete")
    public void deleteById(@RequestParam int id) {
        this.hotelOrganizerService.deleteById(id);
    }

    @PutMapping("/update")
    public void update(@RequestBody HotelOrganizer hotelOrganizer) {
        this.hotelOrganizerService.update(hotelOrganizer);
    }
}
