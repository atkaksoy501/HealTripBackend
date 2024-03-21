package codewizards.heal_trip.business.DTOs.requests;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateAddressRequest {

    @NotNull
    private int id;

    @NotNull
    private AddAddressRequest updatedAddress;
}
