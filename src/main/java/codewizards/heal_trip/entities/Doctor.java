package codewizards.heal_trip.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    //many to one
    @ManyToOne()
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @ManyToOne()
    @JoinColumn(name = "department_id")
    private Department department;

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
}
