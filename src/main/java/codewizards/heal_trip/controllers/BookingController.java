package codewizards.heal_trip.controllers;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import codewizards.heal_trip.business.IBookingService;
import codewizards.heal_trip.entities.*;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    private IBookingService bookingService;
    
    @Autowired
    public BookingController(IBookingService bookingService){
        this.bookingService = bookingService;
    }
    
    @GetMapping("/getAll")
    public List<Booking> getAll() {
        return this.bookingService.getAll();
    }
    
    @PostMapping("/add")
    public Booking add(@RequestBody Booking booking) {
        return this.bookingService.add(booking);
    }
    
    @GetMapping("/getById")
    public Optional<Booking> getById(@RequestParam int id){
        return this.bookingService.getById(id);
    }
    
    @DeleteMapping("/deleteById")
    public void deleteById(@RequestParam int id){
        this.bookingService.deleteById(id);
    }
    
    @PutMapping("/update")
    public Booking update(@RequestBody Booking booking){
        return this.bookingService.update(booking);
    }
}
