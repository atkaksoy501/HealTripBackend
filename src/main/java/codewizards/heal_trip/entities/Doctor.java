package codewizards.heal_trip.entities;

import codewizards.heal_trip.core.entities.BaseEntity;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Data
@Entity
@Table(name="doctors")
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = Doctor.class)
public class Doctor extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "experience_year")
    private int experience_year;

    @Column(name = "doctor_name")
    private String doctorName;

    @Column(name = "doctor_image")
    private byte[] doctorImage;

    @Column(name = "active")
    private boolean active;

    @OneToMany(mappedBy = "doctor")
    private List<Booking> bookings;

    @ManyToOne
    @JoinColumn(name = "hospital_id", referencedColumnName = "id")
    private Hospital hospital;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
}
