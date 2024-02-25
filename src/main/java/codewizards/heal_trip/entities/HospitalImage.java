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
@Table(name="hospital_image")
@Entity
public class HospitalImage extends Image {
    @Column(name = "hospital_image_id")
    private int hospital_image_id;

}