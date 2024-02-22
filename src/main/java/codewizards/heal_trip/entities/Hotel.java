package codewizards.heal_trip.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name="hotel")
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="hotel_id")
    private int hotelId;

    @Column(name="hotel_name")
    private String hotelName;

    @Column(name="address_id")
    private int addressId;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "address_id", referencedColumnName = "address_id") //id = adresteki id
//    private Address address;

    @Column(name="contact_phone")
    private String contactPhone;

    @Column(name="bed_capacity")
    private int bedCapacity;

//    @OneToOne(mappedBy = "hotel", cascade = CascadeType.ALL)
//    private HotelOrganizer organizer;
}
