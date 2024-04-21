package codewizards.heal_trip.business.DTOs.responses.hospital;

import codewizards.heal_trip.business.DTOs.responses.address.AddressForHospitalResponse;
import codewizards.heal_trip.business.DTOs.responses.images.hospitalImage.HospitalImageForHospitalResponse;
import codewizards.heal_trip.entities.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HospitalForDepartmentResponse {
    private int id;

    private int bed_capacity;

    private String hospitalName;

    private boolean active;

    private AddressForHospitalResponse address;

    private String contactPhone;

    private List<Doctor> doctors;

    private List<HospitalImageForHospitalResponse> hospitalImages;
}
