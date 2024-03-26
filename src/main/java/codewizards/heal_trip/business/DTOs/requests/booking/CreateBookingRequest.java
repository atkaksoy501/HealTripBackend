package codewizards.heal_trip.business.DTOs.requests.booking;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookingRequest {
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;
    @NotNull
    private int patient_id;
    @NotNull
    private int hospital_id;
    @NotNull
    private int hotel_id;
    @NotNull
    private int doctor_id;
    @NotNull
    private int retreat_id;
}
