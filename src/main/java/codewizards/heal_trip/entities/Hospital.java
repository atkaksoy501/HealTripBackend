package codewizards.heal_trip.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Data
@Entity
@Table(name="hospitals")
@AllArgsConstructor
@NoArgsConstructor
public class Hospital {
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

    @JsonManagedReference
    @OneToMany(mappedBy = "hospital")
    private List<Department> departments;

    @JsonManagedReference
    @OneToMany(mappedBy = "hospital")
    private List<Booking> bookings;

    @JsonManagedReference
    @OneToMany(mappedBy = "hospital")
    private List<Doctor> doctors;

    @JsonManagedReference
    @OneToMany(mappedBy = "hospital")
    private List<HospitalImage> hospitalImages;

}
