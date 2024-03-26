package codewizards.heal_trip.business.DTOs.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateAddressRequest {
    private int id;
    private String country;
    private String city;
    private String addressDetail;
}
