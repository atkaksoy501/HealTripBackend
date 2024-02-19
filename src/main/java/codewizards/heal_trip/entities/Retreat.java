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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "retreat_id_generator")
    @SequenceGenerator(name="retreat_id_generator", sequenceName = "retreat_id_seq", allocationSize=1)
    @Column(name = "retreat_id")
    private int retreat_id;

    @Column(name = "retreat_name")
    private String retreat_name;

//    @OneToMany(mappedBy = "retreat") //Department class'ı oluşturulduktan sonra açılacak. int olan tip Department olarak değiştirilecek.
    private int department_id;

    @Column(name = "description")
    private String description;
}
