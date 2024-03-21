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

    //@Column(name = "address_id")
    //private int addressId;

    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @Column(name = "contact_phone")
    private String contactPhone;

    @JsonIgnore
    @OneToMany(mappedBy = "hospital", fetch = FetchType.EAGER)
    private List<HospitalDepartment> departments;

    @JsonBackReference(value = "hospital-booking")
    @OneToMany(mappedBy = "hospital")
    private List<Booking> bookings;

//    @JsonManagedReference(value = "doctor-hospital")
    @OneToMany(mappedBy = "hospital", fetch = FetchType.EAGER)
    private List<Doctor> doctors;

//    @JsonManagedReference(value = "hospitalImage-hospital")
    @OneToMany(mappedBy = "hospital", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private List<HospitalImage> hospitalImages;

}
