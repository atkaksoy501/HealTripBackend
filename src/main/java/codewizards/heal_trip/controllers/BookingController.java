package codewizards.heal_trip.controllers;

import java.util.*;
import codewizards.heal_trip.business.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import codewizards.heal_trip.business.IBookingService;
import codewizards.heal_trip.entities.*;

@RestController
@RequestMapping("/booking")
public class BookingController {
    private IBookingService bookingService;
    private IEmailService emailService;
    
    @Autowired
    public BookingController(IBookingService bookingService, IEmailService emailService){
        this.bookingService = bookingService;
        this.emailService = emailService;
    }
    
    @GetMapping("/getAll")
    public List<Booking> getAll() {
        return this.bookingService.getAll();
    }
    
    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody Booking booking) throws IllegalArgumentException{
        try {
            Booking dbBooking = bookingService.add(booking);
            emailService.sendAppointmentEmail(booking);
            return new ResponseEntity<>("Booking with id " + dbBooking.getId() + " has been registered. Email has ben sent to " + booking.getPatient().getEmail(), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
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
