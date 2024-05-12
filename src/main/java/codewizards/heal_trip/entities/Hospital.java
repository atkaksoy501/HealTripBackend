package codewizards.heal_trip.entities;

import codewizards.heal_trip.core.entities.BaseEntity;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Data
@Entity
@Table(name="hospitals")
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = Hospital.class)
public class Hospital extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "bed_capacity")
    private int bed_capacity;

    @Column(name = "hospital_name")
    private String hospitalName;

    @Column(name = "active")
    private boolean active;

    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @Column(name = "contact_phone")
    private String contactPhone;

    @JsonIgnore
    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL)
    private List<HospitalDepartment> departments;

    @OneToMany(mappedBy = "hospital")
    private List<Booking> bookings;

    @OneToMany(mappedBy = "hospital")
    private List<Doctor> doctors;

    @OneToMany(mappedBy = "hospital", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<HospitalImage> hospitalImages;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

}
