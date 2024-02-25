package codewizards.heal_trip.controllers;

import codewizards.heal_trip.business.IHotelService;
import codewizards.heal_trip.entities.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/hotel")
public class HotelsController {

    private IHotelService iHotelService;

    @Autowired
    public HotelsController(IHotelService iHotelService) {
        super();
        this.iHotelService = iHotelService;
    }

    @GetMapping("/getAll")
    public List<Hotel> getAll(){
        return this.iHotelService.getAll();
    }

    @GetMapping("/getAllDesc")
    public List<Hotel> getAllSorted() {
        return this.iHotelService.getAllSorted();
    }

    @GetMapping("/getAllByPage")
    public List<Hotel> getAll(int pageNo, int pageSize){
        return this.iHotelService.getAll(pageNo, pageSize);
    }

    @PostMapping("/add")
    public void add(@RequestBody Hotel hotel) {
        this.iHotelService.add(hotel);
    }

    @GetMapping("/get")
    public Hotel getById(@RequestParam int id) {
        return this.iHotelService.getById(id);
    }

    @DeleteMapping("/delete")
    public void deleteById(@RequestParam int id) {
        this.iHotelService.deleteById(id);
    }

    @PutMapping("/update")
    public void update(@RequestBody Hotel hotel){
        this.iHotelService.update(hotel);
    }
}
