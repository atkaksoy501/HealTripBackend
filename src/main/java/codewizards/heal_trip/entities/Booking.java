package codewizards.heal_trip.entities;

import java.time.LocalDate;
import java.util.Date;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bookings")
public class Booking {
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

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "hospital_id", referencedColumnName = "id")
    private Hospital hospital;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "hotel_id", referencedColumnName = "id")
    private Hotel hotel;

    @JsonBackReference
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
