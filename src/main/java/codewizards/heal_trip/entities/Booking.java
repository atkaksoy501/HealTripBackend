package codewizards.heal_trip.entities;

import java.sql.Timestamp;
import java.util.Date;
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
    
    @Column(name = "patient_id")
    private int patientId;

    @Column(name = "hospital_id")
    private int hospitalId;

    @Column(name = "hotel_id")
    private int hotelId;
    
    @Column(name = "doctor_id")
    private int doctorId;
    
    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;
    
    @Column(name = "booking_date")
    private Date booking_date;
    
    @Column(name = "status")
    private String status;
    
    @Column(name = "retreat_id")
    private int retreatId;
}
