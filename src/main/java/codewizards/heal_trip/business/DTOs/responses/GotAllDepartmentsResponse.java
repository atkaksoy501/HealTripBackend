package codewizards.heal_trip.business.DTOs.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GotAllDepartmentsResponse {
    private int id;
    private String departmentName;
}
