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
public class HospitalOrganizer {
    @Id
    @Column(name="hospital_organizer_id")
    private int hospitalOrganizerId;

    @Column(name="hospital_id")
    private int hospitalId;

//    @OneToOne
//    @MapsId
//    private User user;

}
