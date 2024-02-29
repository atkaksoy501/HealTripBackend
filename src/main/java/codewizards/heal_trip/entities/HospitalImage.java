package codewizards.heal_trip.entities;

import jakarta.persistence.*;
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

    @ManyToOne()
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

}