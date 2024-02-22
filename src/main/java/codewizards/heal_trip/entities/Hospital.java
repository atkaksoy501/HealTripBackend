package codewizards.heal_trip.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name="hospital")
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

}
