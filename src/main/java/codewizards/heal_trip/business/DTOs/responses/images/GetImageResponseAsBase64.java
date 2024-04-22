package codewizards.heal_trip.business.DTOs.responses.images;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetImageResponseAsBase64 {
    private int id;

    private String image;
}
