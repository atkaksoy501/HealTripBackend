package codewizards.heal_trip.api.controllers;

import java.util.*;
import codewizards.heal_trip.business.abstracts.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import codewizards.heal_trip.business.abstracts.IBookingService;
import codewizards.heal_trip.entities.*;

@RestController
@RequestMapping("/booking")
@CrossOrigin
public class BookingsController {
    private IBookingService bookingService;
    private IEmailService emailService;
    
    @Autowired
    public BookingsController(IBookingService bookingService, IEmailService emailService){
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
//            emailService.sendAppointmentEmail(dbBooking);
            return new ResponseEntity<>("Booking with id " + dbBooking.getId() + " has been registered. Email has ben sent to " + booking.getPatient().getEmail(), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
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
    public Booking update(@RequestBody Booking booking, @PathVariable int bookingId){
        return this.bookingService.update(booking);
    }
}
