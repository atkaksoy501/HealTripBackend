package codewizards.heal_trip.business.DTOs.responses.department;

import codewizards.heal_trip.business.DTOs.responses.hospital.HospitalForDepartmentResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentForRetreatResponse {
    private int id;

    private String departmentName;

    private List<HospitalForDepartmentResponse> hospitals;
}
