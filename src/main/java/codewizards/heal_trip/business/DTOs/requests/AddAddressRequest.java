package codewizards.heal_trip.business.DTOs.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddAddressRequest {

    @NotNull
    @Size(min = 2, max = 30)
    private String country;

    @NotNull
    @Size(min = 2, max = 30)
    private String city;

    @NotNull
    @Size(min = 2, max = 100)
    private String addressDetail;
}
