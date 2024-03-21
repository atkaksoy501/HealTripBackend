package codewizards.heal_trip.business.DTOs.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GotAddressResponse {
    private int id;
    private String country;
    private String city;
    private String addressDetail;
}
