package codewizards.heal_trip.business.DTOs.requests.department;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDepartmentRequest {

    @Size(min = 3, max = 50)
    private String departmentName;

    private List<Integer> hospital_ids;

    private List<Integer> retreat_ids;
}
