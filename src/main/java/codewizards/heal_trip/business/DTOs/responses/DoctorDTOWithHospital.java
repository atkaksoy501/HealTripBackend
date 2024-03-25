package codewizards.heal_trip.business.DTOs.responses;

import codewizards.heal_trip.entities.Hospital;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDTOWithHospital {
    private int id;

    private int experience_year;

    private String doctorName;

    private byte[] doctorImage;

    private DepartmentDTO department;

    private HospitalForDoctorResponse hospital;
}
