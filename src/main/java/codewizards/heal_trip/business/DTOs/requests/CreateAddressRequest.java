package codewizards.heal_trip.business.DTOs.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateAddressRequest {
    @NotNull
    @Size(min = 2, max = 15)
    private String country;
    @NotNull
    @Size(min = 2, max = 15)
    private String city;
    @NotNull
    @Size(min = 2, max = 50)
    private String addressDetail;
}
