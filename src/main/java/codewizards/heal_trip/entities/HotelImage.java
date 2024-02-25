package codewizards.heal_trip.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="hotel_image")
@Entity
public class HotelImage extends Image {
    @Column(name = "hotel_image_id")
    private int hotel_image_id;

}
