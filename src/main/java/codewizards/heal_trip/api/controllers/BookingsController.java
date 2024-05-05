package codewizards.heal_trip.api.controllers;

import java.util.*;

import codewizards.heal_trip.business.DTOs.requests.booking.CreateBookingRequest;
import codewizards.heal_trip.business.DTOs.responses.booking.CreatedBookingResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import codewizards.heal_trip.business.abstracts.IBookingService;
import codewizards.heal_trip.entities.*;

@Tag(name = "Booking Management", description = "Booking Management APIs")
@RestController
@RequestMapping("/booking")
@CrossOrigin
@AllArgsConstructor
public class BookingsController {
    private IBookingService bookingService;

    @Operation(summary = "Get all Bookings")
    @GetMapping("/getAll")
    public List<Booking> getAll() {
        return this.bookingService.getAll();
    }

    @Operation(summary = "Create new Booking")
    @PostMapping("/add")
    public ResponseEntity<CreatedBookingResponse> add(@Valid @RequestBody CreateBookingRequest booking){
        CreatedBookingResponse dbBooking = bookingService.add(booking);
        return new ResponseEntity<>(dbBooking, HttpStatus.OK);
    }

    @Operation(summary = "Get Booking by ID")
    @GetMapping("/get/{id}")
    public Booking getById(@PathVariable int id){
        return this.bookingService.getById(id);
    }

    @Operation(summary = "Delete Booking by ID")
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable int id){
        this.bookingService.deleteById(id);
    }

    @Operation(summary = "Update Booking by ID")
    @PutMapping("/update/{id}")
    public Booking update(@Valid @RequestBody Booking booking, @PathVariable int bookingId){
        return this.bookingService.update(booking);
    }
}
