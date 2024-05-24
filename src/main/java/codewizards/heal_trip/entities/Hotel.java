package codewizards.heal_trip.entities;

import codewizards.heal_trip.core.entities.BaseEntity;
import com.fasterxml.jackson.annotation.*;
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
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "hotelId",
        scope = Hotel.class)
public class Hotel extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int hotelId;

    @Column(name="hotel_name")
    private String hotelName;

    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @Column(name="contact_phone")
    private String contactPhone;

    @Column(name="bed_capacity")
    private int bedCapacity;

    @JsonBackReference(value = "hotel-booking")
    @OneToMany(mappedBy = "hotel")
    private List<Booking> bookings;

    @OneToMany(mappedBy = "hotel", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<HotelImage> hotelImages;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
}
