package codewizards.heal_trip.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="hospital_organizer")
@AllArgsConstructor
@NoArgsConstructor
public class HospitalOrganizer extends User{

    @OneToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

//    @OneToOne
//    @MapsId
//    private User user;

}
