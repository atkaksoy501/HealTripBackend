package codewizards.heal_trip.business.rules;

import codewizards.heal_trip.business.messages.HotelMessages;
import codewizards.heal_trip.dataAccess.HotelDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HotelBusinessRules {
    private final HotelDao hotelDao;

    public void checkIfHotelExists(int hotelId) {
        if (!hotelDao.existsById(hotelId)) {
            throw new IllegalArgumentException(HotelMessages.HOTEL_NOT_FOUND);
        }
    }
}
