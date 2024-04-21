package codewizards.heal_trip.business.DTOs.responses.retreat;

import codewizards.heal_trip.business.DTOs.responses.department.DepartmentDTO;
import codewizards.heal_trip.business.DTOs.responses.images.GetImageResponse;
import codewizards.heal_trip.entities.RetreatImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddedRetreatResponse {

    private int id;

    private String retreat_name;

    private DepartmentDTO department;

    private String description;

    private GetImageResponse image;
}
