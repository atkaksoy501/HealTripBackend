package codewizards.heal_trip.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name="doctor")
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "hospital_id")
    private int hospitalId;

    @Column(name = "department_id")
    private int departmentId;

    @Column(name = "experience_year")
    private int experience_year;

    @Column(name = "doctor_name")
    private String doctorName;

    @Column(name = "doctor_image")
    private String doctorImage;

    @Column(name = "active")
    private boolean active;
}
