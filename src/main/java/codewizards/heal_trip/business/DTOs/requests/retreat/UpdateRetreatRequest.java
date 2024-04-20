package codewizards.heal_trip.business.DTOs.requests.retreat;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateRetreatRequest {
    @Size(min = 3, max = 50)
    private String retreat_name;

    @Size(min = 3, max = 1000)
    private String description;

    private int departmentId;

    private int imageId;
}
