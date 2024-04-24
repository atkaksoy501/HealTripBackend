package codewizards.heal_trip.business.rules;

import codewizards.heal_trip.business.messages.BookingMessages;
import codewizards.heal_trip.core.utilities.exceptions.types.BusinessException;
import codewizards.heal_trip.dataAccess.BookingDao;
import codewizards.heal_trip.dataAccess.HotelDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookingBusinessRules {
    private final BookingDao bookingDao;
    private final HotelDao hotelDao;

    public void checkIfBookingExists(int bookingId) {
        if (!bookingDao.existsById(bookingId)) {
            throw new BusinessException(BookingMessages.BOOKING_NOT_FOUND);
        }
    }

    public boolean checkIfBookingsHotelExists(int hotelId) {
        if (hotelId == 0) {
            return false;
        }
        if (!hotelDao.existsById(hotelId)) {
            throw new BusinessException(BookingMessages.BOOKINGS_HOTEL_NOT_FOUND);
        }
        return true;
    }

    public boolean checkIfBookingsHospitalExists(int hospitalId) {
        if (hospitalId == 0) {
            return false;
        }
        if (!hotelDao.existsById(hospitalId)) {
            throw new BusinessException(BookingMessages.BOOKINGS_HOSPITAL_NOT_FOUND);
        }
        return true;
    }

    public boolean checkIfBookingsDoctorExists(int doctorId) {
        if (doctorId == 0) {
            return false;
        }
        if (!hotelDao.existsById(doctorId)) {
            throw new BusinessException(BookingMessages.BOOKINGS_DOCTOR_NOT_FOUND);
        }
        return true;
    }
}
