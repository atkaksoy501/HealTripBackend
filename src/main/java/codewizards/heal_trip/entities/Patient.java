package codewizards.heal_trip.entities;

import jakarta.persistence.*;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="patient")
@Entity
public class Patient {
    @Id
    @Column(name = "patient_id")
    private int patient_id;

    @Column(name = "birth_year")
    private int birth_year;

    @Column(name = "gender")
    private char gender;

    @Column(name = "patient_height")
    private int patient_height;

    @Column(name = "patient_weight")
    private int patient_weight;
}
