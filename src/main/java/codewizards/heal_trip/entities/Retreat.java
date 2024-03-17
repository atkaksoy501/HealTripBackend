package codewizards.heal_trip.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import lombok.*;

import java.util.List;

@Data
@Entity
@Table(name="retreats")
@AllArgsConstructor
@NoArgsConstructor
public class Retreat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "retreat_name")
    private String retreat_name;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;

    @Column(name = "description")
    private String description;

    @OneToOne
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private RetreatImage image;

}
