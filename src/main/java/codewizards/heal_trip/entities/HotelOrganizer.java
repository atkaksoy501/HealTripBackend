package codewizards.heal_trip.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="hotel_organizer")
@AllArgsConstructor
@NoArgsConstructor
public class HotelOrganizer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="hotel_organizer_id")
    private int hotelOrganizerId;

//    @OneToOne
//    @MapsId
//    private User user;
     @Column(name="hotel_id")
     private int hotelId;
//    @OneToOne
//    @JoinColumn(name = "hotel_id")
      //private Hotel hotel;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "address_id", referencedColumnName = "address_id") //id = adresteki id
//    private Address address;
}
