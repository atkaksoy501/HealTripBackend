package codewizards.heal_trip.business.DTOs.responses.doctor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorForDepartmentResponse {
    private int id;

    private int experience_year;

    private String doctorName;

    private String doctorImage;
}
