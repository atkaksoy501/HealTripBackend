package codewizards.heal_trip.api.controllers;

import java.util.*;

import codewizards.heal_trip.business.DTOs.requests.booking.CreateBookingRequest;
import codewizards.heal_trip.business.DTOs.responses.booking.CreatedBookingResponse;
import codewizards.heal_trip.business.abstracts.IEmailService;
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
    private IEmailService emailService;
    
    @GetMapping("/getAll")
    public List<Booking> getAll() {
        return this.bookingService.getAll();
    }

    @PostMapping("/add")
    public ResponseEntity<CreatedBookingResponse> add(@Valid @RequestBody CreateBookingRequest booking) throws IllegalArgumentException{
        try {
            CreatedBookingResponse dbBooking = bookingService.add(booking);
//            emailService.sendAppointmentEmail(dbBooking); //todo: uncomment this line
            return new ResponseEntity<>(dbBooking, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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
