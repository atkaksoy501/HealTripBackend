package codewizards.heal_trip.business.DTOs.responses.booking;

import codewizards.heal_trip.entities.enums.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingForPatientResponse {
    private int id;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate bookingDate;
    private BookingStatus status;
    private HospitalForBookingResponse hospital;
    private HotelForBookingResponse hotel;
    private DoctorForBookingResponse doctor;
    private RetreatForBookingResponse retreat;
    private String description;
}
