package codewizards.heal_trip.business.DTOs.responses.hotel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllHotelsForAiResponse {
    private String hotelName;
    private String description;
}
