package codewizards.heal_trip.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="hospital_organizers")
@AllArgsConstructor
@NoArgsConstructor
public class HospitalOrganizer extends User{

    @OneToOne
    @JoinColumn(name = "hospital_id", referencedColumnName = "id")
    private Hospital hospital;

//    @OneToOne
//    @MapsId
//    private User user;

}
