package codewizards.heal_trip.business.DTOs.responses.hospital;

import codewizards.heal_trip.entities.HospitalImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GotHospitalsByDepartmentIdResponse {
    private int id;
    private String hospitalName;
    private List<HospitalImage> hospitalImages;
}
