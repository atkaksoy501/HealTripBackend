package codewizards.heal_trip.business.DTOs.responses.patient;

import codewizards.heal_trip.entities.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatedPatientResponse {
    private int id;

    private String first_name;

    private String last_name;

    private String email;

    private String phone_number;

    private LocalDate birth_date;

    private Gender gender;

    private int patient_height;

    private int patient_weight;
}
