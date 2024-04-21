package codewizards.heal_trip.business.DTOs.responses.images.hospitalImage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HospitalImageForHospitalResponse {
    private int id;

    private byte[] image;
}
