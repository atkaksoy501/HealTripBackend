package codewizards.heal_trip.business.DTOs.responses.images;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetImageResponse {
    private int id;

    private byte[] image;
}
