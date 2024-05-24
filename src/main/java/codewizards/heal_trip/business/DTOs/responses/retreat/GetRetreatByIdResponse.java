package codewizards.heal_trip.business.DTOs.responses.retreat;

import codewizards.heal_trip.business.DTOs.responses.department.DepartmentForRetreatResponse;
import codewizards.heal_trip.business.DTOs.responses.images.GetImageResponse;
import codewizards.heal_trip.entities.RetreatImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetRetreatByIdResponse {
    private int id;

    private String retreat_name;

    private DepartmentForRetreatResponse department;

    private String description;

    private GetImageResponse image;
}
