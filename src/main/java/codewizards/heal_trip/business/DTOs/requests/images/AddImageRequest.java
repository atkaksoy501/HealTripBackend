package codewizards.heal_trip.business.DTOs.requests.images;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddImageRequest {
    @NotNull
    private byte[] image;
}
