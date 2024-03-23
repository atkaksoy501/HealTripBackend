package codewizards.heal_trip.business.concretes;

import java.time.LocalDateTime;
import java.util.*;

import codewizards.heal_trip.business.abstracts.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import codewizards.heal_trip.dataAccess.*;
import codewizards.heal_trip.entities.Booking;

@Service
public class BookingService implements IBookingService {
    private BookingDao bookingDao;
    @Autowired
    public BookingService(BookingDao bookingDao) {
        this.bookingDao = bookingDao;
    }
    
    @Override
    public List<Booking> getAll() {
        return this.bookingDao.findAll();
    }
    
    @Override
    public Booking getById(int id) {
        return this.bookingDao.findById(id).orElse(null);
    }
    
    @Override
    public Booking add(Booking booking) {
        booking.setCreateDate(LocalDateTime.now());
        return this.bookingDao.save(booking);
    }
    
    @Override
    public void deleteById(int id) {
        this.bookingDao.deleteById(id);
    }
    
    @Override
    public Booking update(Booking booking) {
        return this.bookingDao.save(booking);
    }
}
