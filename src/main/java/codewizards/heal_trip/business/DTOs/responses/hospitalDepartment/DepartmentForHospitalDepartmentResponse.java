package codewizards.heal_trip.business.DTOs.responses.hospitalDepartment;

import codewizards.heal_trip.business.DTOs.responses.department.DepartmentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentForHospitalDepartmentResponse {
    private DepartmentDTO department;
}
