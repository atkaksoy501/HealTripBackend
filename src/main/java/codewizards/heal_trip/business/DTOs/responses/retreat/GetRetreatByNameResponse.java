package codewizards.heal_trip.business.DTOs.responses.retreat;

import codewizards.heal_trip.business.DTOs.responses.department.DepartmentForRetreatResponse;
import codewizards.heal_trip.business.DTOs.responses.images.GetImageResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetRetreatByNameResponse {
    private int id;

    private String retreat_name;

    private GetImageResponse image;
}
