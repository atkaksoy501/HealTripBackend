package codewizards.heal_trip.api.controllers;

import codewizards.heal_trip.business.abstracts.IHotelService;
import codewizards.heal_trip.entities.Hotel;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
@CrossOrigin
@AllArgsConstructor
public class HotelsController {

    private final IHotelService hotelService;

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
    public ResponseEntity<Hotel> add(@Valid @RequestBody Hotel hotel) {
        return new ResponseEntity<>(this.hotelService.add(hotel), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public Hotel getById(@PathVariable int id) {
        return this.hotelService.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable int id) {
        this.hotelService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public void update(@Valid @RequestBody Hotel hotel, @PathVariable int id){
        this.hotelService.update(hotel);
    }
}
