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
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    //@Column(name = "patient_id")
    //private int patientId;

    //@Column(name = "hospital_id")
    //private int hospitalId;

    //@Column(name = "hotel_id")
    //private int hotelId;
    //
    //@Column(name = "doctor_id")
    //private int doctorId;
    
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
    
    //@Column(name = "retreat_id")
    //private int retreatId;
    
    @ManyToOne()
    @JoinColumn(name = "patient_id")
    private Patient patient;
    
    //@OneToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "hospital_id", referencedColumnName = "id")
    //private Hospital hospital;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hotel_id", referencedColumnName = "id")
    private Hotel hotel;
    
    @ManyToOne()
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "retreat_id", referencedColumnName = "id")
    private Retreat retreat;

    @ManyToOne()
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;
}
