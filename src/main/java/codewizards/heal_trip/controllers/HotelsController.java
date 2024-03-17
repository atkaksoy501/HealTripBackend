package codewizards.heal_trip.controllers;

import codewizards.heal_trip.business.IHotelService;
import codewizards.heal_trip.entities.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
@CrossOrigin
public class HotelsController {

    private IHotelService hotelService;

    @Autowired
    public HotelsController(IHotelService hotelService) {
        super();
        this.hotelService = hotelService;
    }

    @GetMapping("/getAll")
    public List<Hotel> getAll(){
        return this.hotelService.getAll();
    }

    @GetMapping("/getAllDesc")
    public List<Hotel> getAllSorted() {
        return this.hotelService.getAllSorted();
    }

    @GetMapping("/getAllByPage")
    public List<Hotel> getAll(int pageNo, int pageSize){
        return this.hotelService.getAll(pageNo, pageSize);
    }

    @PostMapping("/add")
    public int add(@RequestBody Hotel hotel) {
        return this.hotelService.add(hotel);
    }

    @GetMapping("/get")
    public Hotel getById(@RequestParam int id) {
        return this.hotelService.getById(id);
    }

    @DeleteMapping("/delete")
    public void deleteById(@RequestParam int id) {
        this.hotelService.deleteById(id);
    }

    @PutMapping("/update")
    public void update(@RequestBody Hotel hotel){
        this.hotelService.update(hotel);
    }
}
