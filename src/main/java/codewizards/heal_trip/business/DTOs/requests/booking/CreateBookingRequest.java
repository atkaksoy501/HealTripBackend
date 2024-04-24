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
    private LocalDate startDate;

    private LocalDate endDate;

    @NotNull
    private int patient_id;

    @NotNull
    private int retreat_id;

    @NotNull
    private String description;

    private int hospital_id;

    private int hotel_id;

    private int doctor_id;
}
