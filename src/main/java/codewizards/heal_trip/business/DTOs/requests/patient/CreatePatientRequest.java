package codewizards.heal_trip.business.DTOs.requests.patient;

import codewizards.heal_trip.entities.enums.Gender;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePatientRequest {
    @NotNull
    @Size(min = 2, max = 50)
    private String first_name;

    @NotNull
    @Size(min = 2, max = 50)
    private String last_name;

    @NotNull
    @Size(min = 2, max = 50)
    private String email;

    @NotNull
    @Size(min = 11, max = 11)
    private String phone_number;

    @NotNull
    @Size(min = 6, max = 50)
    private String password;

    @NotNull
    private LocalDate birth_date;

    @NotNull
    private Gender gender;

    @NotNull
    private int patient_height;

    @NotNull
    private int patient_weight;
}
