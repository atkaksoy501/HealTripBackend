package codewizards.heal_trip.entities;

import jakarta.persistence.*;

import lombok.*;

@Data
@Entity
@Table(name="retreat")
@AllArgsConstructor
@NoArgsConstructor
public class Retreat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "retreat_name")
    private String retreat_name;

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;

    @Column(name = "description")
    private String description;
}
