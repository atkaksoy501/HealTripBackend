package codewizards.heal_trip.business.DTOs.responses;

import codewizards.heal_trip.entities.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GotHospitalByIdResponse {
    private int id;

    private int bed_capacity;

    private String hospitalName;

    private Address address;

    private String contactPhone;

    private List<DepartmentForHospitalDepartmentResponse> departments;

    private List<DoctorDTO> doctors;

    private List<HospitalImage> hospitalImages;
}
