package codewizards.heal_trip.business.abstracts;

import java.util.*;
import codewizards.heal_trip.entities.*;

public interface IBookingService {
    List<Booking> getAll();
    
    Booking getById(int id);
    
    Booking add(Booking booking);
    
    void deleteById(int id);
    
    Booking update(Booking booking);
}
