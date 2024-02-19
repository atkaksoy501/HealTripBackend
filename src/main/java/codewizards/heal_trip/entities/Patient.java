package codewizards.heal_trip.entities;

import jakarta.persistence.*;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="patient")
@Entity
public class Patient extends User{
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_id_generator")
//    @SequenceGenerator(name="patient_id_generator", sequenceName = "patient_id_seq", allocationSize=1)
//    @Column(name = "patient_id")
//    private int patient_id;

    @Column(name = "birth_year")
    private int birth_year;

    @Column(name = "gender")
    private char gender;

    @Column(name = "patient_height")
    private int patient_height;

    @Column(name = "patient_weight")
    private int patient_weight;
}
