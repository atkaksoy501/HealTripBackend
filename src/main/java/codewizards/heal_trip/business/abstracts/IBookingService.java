package codewizards.heal_trip.business.abstracts;

import java.util.*;

import codewizards.heal_trip.business.DTOs.requests.booking.CreateBookingRequest;
import codewizards.heal_trip.business.DTOs.responses.booking.CreatedBookingResponse;
import codewizards.heal_trip.entities.*;

public interface IBookingService {
    List<Booking> getAll();
    
    Booking getById(int id);
    
    CreatedBookingResponse add(CreateBookingRequest booking);
    
    void deleteById(int id);
    
    Booking update(Booking booking);
}
