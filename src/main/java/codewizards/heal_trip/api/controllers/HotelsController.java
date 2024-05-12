package codewizards.heal_trip.api.controllers;

import codewizards.heal_trip.business.DTOs.responses.hotel.GetAllHotelsForAiResponse;
import codewizards.heal_trip.business.abstracts.IHotelService;
import codewizards.heal_trip.entities.Hotel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Hotel Management", description = "Hotel Management APIs")
@RestController
@RequestMapping("/hotel")
@CrossOrigin
@AllArgsConstructor
public class HotelsController {

    private final IHotelService hotelService;

    @Operation(summary = "Get all Hotels")
    @GetMapping("/getAll")
    public List<Hotel> getAll(){
        return this.hotelService.getAll();
    }

    @Operation(summary = "Get all Hotels Sorted")
    @GetMapping("/getAllDesc")
    public List<Hotel> getAllSorted() {
        return this.hotelService.getAllSorted();
    }

    @Operation(summary = "Get all Hotels by Page")
    @GetMapping("/getAllByPage")
    public List<Hotel> getAll(int pageNo, int pageSize){
        return this.hotelService.getAll(pageNo, pageSize);
    }

    @Operation(summary = "Add new Hotel")
    @PostMapping("/add")
    public ResponseEntity<Hotel> add(@Valid @RequestBody Hotel hotel) {
        return new ResponseEntity<>(this.hotelService.add(hotel), HttpStatus.OK);
    }

    @Operation(summary = "Get Hotel by ID")
    @GetMapping("/get/{id}")
    public Hotel getById(@PathVariable int id) {
        return this.hotelService.getById(id);
    }

    @Operation(summary = "Delete Hotel by ID")
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable int id) {
        this.hotelService.deleteById(id);
    }

    @Operation(summary = "Update Hotel by ID")
    @PutMapping("/update/{id}")
    public void update(@Valid @RequestBody Hotel hotel, @PathVariable int id){
        this.hotelService.update(hotel);
    }

    @Operation(summary = "Get all Hotels For AI")
    @GetMapping("/getAllForAI")
    public List<GetAllHotelsForAiResponse> getAllForAI() {
        return this.hotelService.getAllHotelsForAI();
    }

    @Operation(summary = "Get all Hotels Count")
    @GetMapping("/count")
    public Long getCount() {
        return this.hotelService.getHotelCount();
    }
}
