package codewizards.heal_trip.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="patient")
@Entity
public class Patient extends User{

    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birth_date;

    @Column(name = "gender")
    private char gender;
    
    @Column(name = "patient_height")
    private int patient_height;

    @Column(name = "patient_weight")
    private int patient_weight;
    
    @OneToMany(mappedBy = "patient")
    private List<Booking> bookings;
}
