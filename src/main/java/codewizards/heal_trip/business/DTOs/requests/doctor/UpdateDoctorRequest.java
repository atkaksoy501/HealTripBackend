package codewizards.heal_trip.business.DTOs.requests.doctor;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDoctorRequest {
    private int experience_year;

    @Size(min = 2, max = 30)
    private String doctorName;

    private String doctorImage;

    private int hospital_id;

    private int department_id;

    private List<Integer> booking_ids;

    private String description;
}
