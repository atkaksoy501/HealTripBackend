package codewizards.heal_trip.business.DTOs.responses;

import codewizards.heal_trip.entities.RetreatImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GotRetreatByDepartmentIdResponse {
    private int id;

    private String retreat_name;

    private String description;

    private RetreatImage image;
}
