package codewizards.heal_trip.business.DTOs.responses.booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HospitalForBookingResponse {
    private int id;
    private String hospitalName;
}
