package codewizards.heal_trip.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Data
@Entity
@Table(name="doctors")
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    //@Column(name = "hospital_id")
    //private int hospitalId;

    //@Column(name = "department_id")
    //private int departmentId;

    @Column(name = "experience_year")
    private int experience_year;

    @Column(name = "doctor_name")
    private String doctorName;

    @Column(name = "doctor_image")
    private byte[] doctorImage;

    @Column(name = "active")
    private boolean active;

    @JsonManagedReference
    @OneToMany(mappedBy = "doctor")
    private List<Booking> bookings;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "hospital_id", referencedColumnName = "id")
    private Hospital hospital;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
