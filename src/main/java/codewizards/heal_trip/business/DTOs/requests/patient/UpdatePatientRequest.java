package codewizards.heal_trip.business.DTOs.requests.patient;

import codewizards.heal_trip.entities.enums.Gender;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePatientRequest {
    @Size(min = 2, max = 50)
    private String first_name;

    @Size(min = 2, max = 50)
    private String last_name;

    @Size(min = 2, max = 50)
    private String email;

    @Size(min = 11, max = 11)
    private String phone_number;

    @Size(min = 6, max = 50)
    private String password;

    @Size(min = 6, max = 50)
    private String oldPassword;

    private LocalDate birth_date;

    private Gender gender;

    private int patient_height;

    private int patient_weight;

    private List<Integer> booking_ids;
}
