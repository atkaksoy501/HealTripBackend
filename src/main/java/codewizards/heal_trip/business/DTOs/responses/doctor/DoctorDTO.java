package codewizards.heal_trip.business.DTOs.responses.doctor;


import codewizards.heal_trip.business.DTOs.responses.department.DepartmentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDTO {
    private int id;

    private int experience_year;

    private String doctorName;

    private byte[] doctorImage;

    private DepartmentDTO department;
}
