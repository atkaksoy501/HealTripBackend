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

    @Column(name = "address_id")
    private int addressId;

    @Column(name = "contact_phone")
    private String contactPhone;

    @JsonIgnore
    @OneToMany(mappedBy = "hospital")
    private List<Department> departments;
}
