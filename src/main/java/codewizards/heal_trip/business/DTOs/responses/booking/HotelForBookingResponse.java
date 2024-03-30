package codewizards.heal_trip.business.DTOs.responses.booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelForBookingResponse {
    private int id;
    private String hotelName;
}
