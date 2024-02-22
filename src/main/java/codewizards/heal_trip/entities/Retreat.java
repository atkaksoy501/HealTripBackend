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

//    @OneToMany(mappedBy = "retreat") //Department class'ı oluşturulduktan sonra açılacak. int olan tip Department olarak değiştirilecek.
    private int department_id;

    @Column(name = "description")
    private String description;
}
