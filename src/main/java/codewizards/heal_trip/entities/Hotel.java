package codewizards.heal_trip.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name="hotels")
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int hotelId;

    @Column(name="hotel_name")
    private String hotelName;

//    @Column(name="address_id")
//    private int addressId;

    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id") //id = adresteki id
    private Address address;

    @Column(name="contact_phone")
    private String contactPhone;

    @Column(name="bed_capacity")
    private int bedCapacity;

//    @OneToOne(mappedBy = "hotel", cascade = CascadeType.ALL)
//    private HotelOrganizer organizer;

    @JsonIgnore
    @OneToMany(mappedBy = "hotel")
    private List<Booking> bookings;

    @JsonIgnore
    @OneToMany(mappedBy = "hotel")
    private List<HotelImage> hotelImages;
}
