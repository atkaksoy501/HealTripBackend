package codewizards.heal_trip.business.DTOs.requests.hospital;

import codewizards.heal_trip.business.DTOs.requests.address.CreateAddressRequest;
import codewizards.heal_trip.business.DTOs.requests.images.AddImageRequest;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddHospitalRequest {
    @NotNull
    private int bed_capacity;

    @NotNull
    @Size(min = 2, max = 50)
    private String hospitalName;

    @NotNull
    private CreateAddressRequest address;

    @NotNull
    @Size(min = 11, max = 11)
    private String contactPhone;

    @NotNull
    private List<Integer> department_ids;

    @NotNull
    private List<AddImageRequest> hospitalImages;
}
