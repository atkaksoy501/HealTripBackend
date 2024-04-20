package codewizards.heal_trip.business.DTOs.requests.retreat;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddRetreatRequest {
    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    @NotNull
    @Size(min = 3, max = 1000)
    private String description;

    @NotNull
    private int departmentId;

    @NotNull
    private int imageId;
}
