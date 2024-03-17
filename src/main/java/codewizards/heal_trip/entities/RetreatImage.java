package codewizards.heal_trip.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="retreat_images")
@Entity
public class RetreatImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "image")
    private byte[] image;

    @OneToOne
    @JoinColumn(name = "retreat_id", referencedColumnName = "id")
    private Retreat retreat;
}
