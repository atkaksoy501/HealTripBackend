package codewizards.heal_trip.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "country")
    private String country;
    
    @Column(name = "city")
    private String city;
    
    @Column(name = "address_detail")
    private String addressDetail;
}
