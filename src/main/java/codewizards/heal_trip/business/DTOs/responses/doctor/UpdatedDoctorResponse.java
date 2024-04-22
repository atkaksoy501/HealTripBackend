package codewizards.heal_trip.business.DTOs.responses.doctor;

import codewizards.heal_trip.business.DTOs.responses.department.DepartmentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatedDoctorResponse {
    private int id;

    private int experience_year;

    private String doctorName;

    private String doctorImage;

    private boolean active;

    private List<Integer> booking_ids;

    private HospitalForDoctorResponse hospital;

    private DepartmentDTO department;

    private LocalDateTime updateDate;
}
