package codewizards.heal_trip.business.DTOs.responses.address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressForHospitalResponse {
    private int id;

    private String country;

    private String city;

    private String addressDetail;
}
