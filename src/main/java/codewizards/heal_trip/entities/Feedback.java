package codewizards.heal_trip.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name="feedback")
@AllArgsConstructor
@NoArgsConstructor
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "booking_id", referencedColumnName = "id")
    private Booking booking;

    @Column(name = "comment")
    private String comment;

    @Column(name = "rating")
    private int rating;
}
