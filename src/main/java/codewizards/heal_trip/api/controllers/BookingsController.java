package codewizards.heal_trip.api.controllers;

import java.util.*;

import codewizards.heal_trip.business.DTOs.requests.booking.CreateBookingRequest;
import codewizards.heal_trip.business.DTOs.responses.booking.CreatedBookingResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import codewizards.heal_trip.business.abstracts.IBookingService;
import codewizards.heal_trip.entities.*;

@RestController
@RequestMapping("/booking")
@CrossOrigin
@AllArgsConstructor
public class BookingsController {
    private IBookingService bookingService;

    @GetMapping("/getAll")
    public List<Booking> getAll() {
        return this.bookingService.getAll();
    }

    @PostMapping("/add")
    public ResponseEntity<CreatedBookingResponse> add(@Valid @RequestBody CreateBookingRequest booking){
        CreatedBookingResponse dbBooking = bookingService.add(booking);
        return new ResponseEntity<>(dbBooking, HttpStatus.OK);
    }
    
    @GetMapping("/get/{id}")
    public Booking getById(@PathVariable int id){
        return this.bookingService.getById(id);
    }
    
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable int id){
        this.bookingService.deleteById(id);
    }
    
    @PutMapping("/update/{id}")
    public Booking update(@Valid @RequestBody Booking booking, @PathVariable int bookingId){
        return this.bookingService.update(booking);
    }
}
