package codewizards.heal_trip.business.DTOs.responses.hospital;

import codewizards.heal_trip.business.DTOs.responses.address.AddressForHospitalResponse;
import codewizards.heal_trip.business.DTOs.responses.doctor.DoctorDTO;
import codewizards.heal_trip.business.DTOs.responses.hospitalDepartment.DepartmentForHospitalDepartmentResponse;
import codewizards.heal_trip.business.DTOs.responses.images.GetImageResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddedHospitalResponse {
    private int id;

    private int bed_capacity;

    private String hospitalName;

    private AddressForHospitalResponse address;

    private String contactPhone;

    private List<DepartmentForHospitalDepartmentResponse> departments;

    private List<DoctorDTO> doctors;

    private List<GetImageResponse> hospitalImages;
}
