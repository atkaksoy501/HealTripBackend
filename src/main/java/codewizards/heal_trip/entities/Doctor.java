package codewizards.heal_trip.entities;

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

//    @JsonBackReference(value = "doctor-booking")
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Booking> bookings;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE)
//    @JoinColumn(name = "hospital_id", referencedColumnName = "id")
    private Hospital hospital;

//    @JsonManagedReference(value = "doctor-department")
//    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "department_id")
    private Department department;
}
