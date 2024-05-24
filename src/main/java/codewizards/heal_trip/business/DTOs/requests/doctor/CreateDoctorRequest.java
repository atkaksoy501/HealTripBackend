package codewizards.heal_trip.business.DTOs.requests.doctor;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDoctorRequest {
    @NotNull
    private int experience_year;

    @NotNull
    @Size(min = 2, max = 30)
    private String doctorName;

    @NotNull
    private String doctorImage;

    @NotNull
    private int hospital_id;

    @NotNull
    private int department_id;

    @NotNull
    private String description;
}
