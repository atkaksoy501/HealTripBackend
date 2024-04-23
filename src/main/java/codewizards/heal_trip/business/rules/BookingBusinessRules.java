package codewizards.heal_trip.business.rules;

import codewizards.heal_trip.business.messages.BookingMessages;
import codewizards.heal_trip.core.utilities.exceptions.types.BusinessException;
import codewizards.heal_trip.dataAccess.BookingDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookingBusinessRules {
    private final BookingDao bookingDao;

    public void checkIfBookingExists(int bookingId) {
        if (!bookingDao.existsById(bookingId)) {
            throw new BusinessException(BookingMessages.BOOKING_NOT_FOUND);
        }
    }
}
