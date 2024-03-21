package codewizards.heal_trip.entities;

import java.time.LocalDate;
import java.util.Date;

import codewizards.heal_trip.core.entities.BaseEntity;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bookings")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = Booking.class)
public class Booking extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate startDate;

    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate endDate;
    
    @Column(name = "booking_date")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate booking_date;
    
    @Column(name = "status")
    private String status;

//    @JsonManagedReference(value = "patient-booking")
    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;

//    @JsonManagedReference(value = "hospital-booking")
    @ManyToOne
    @JoinColumn(name = "hospital_id", referencedColumnName = "id")
    private Hospital hospital;

//    @JsonManagedReference(value = "hotel-booking")
    @ManyToOne
    @JoinColumn(name = "hotel_id", referencedColumnName = "id")
    private Hotel hotel;

//    @JsonManagedReference(value = "doctor-booking")
//    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private Doctor doctor;
    
    @OneToOne
    @JoinColumn(name = "retreat_id", referencedColumnName = "id")
    private Retreat retreat;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "feedback_id", referencedColumnName = "id")
    private Feedback feedback;
}
