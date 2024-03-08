package codewizards.heal_trip.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @OneToMany(mappedBy = "hospital")
    private List<Department> departments;

    @JsonIgnore
    @OneToMany(mappedBy = "hospital")
    private List<Booking> bookings;

    @JsonIgnore
    @OneToMany(mappedBy = "hospital")
    private List<Doctor> doctors;

    @JsonIgnore
    @OneToMany(mappedBy = "hospital")
    private List<HospitalImage> hospitalImages;

}
