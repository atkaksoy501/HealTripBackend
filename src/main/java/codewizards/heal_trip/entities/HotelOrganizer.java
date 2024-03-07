package codewizards.heal_trip.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="hotel_organizers")
@AllArgsConstructor
@NoArgsConstructor
public class HotelOrganizer extends User{
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name="hotel_organizer_id")
//    private int hotelOrganizerId;

    @OneToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
}
