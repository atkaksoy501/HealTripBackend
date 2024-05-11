package codewizards.heal_trip.business.DTOs.requests.retreat;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetRetreatByNameRequest {
    @NotNull
    private List<String> retreatNames;
}
