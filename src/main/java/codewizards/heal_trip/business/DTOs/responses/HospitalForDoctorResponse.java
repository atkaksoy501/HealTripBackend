package codewizards.heal_trip.business.DTOs.responses;

import codewizards.heal_trip.entities.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HospitalForDoctorResponse {
    private int id;

    private String hospitalName;

    private List<HospitalImage> hospitalImages;
}
