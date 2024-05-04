package codewizards.heal_trip.business.DTOs.requests.email;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendEmailRequest {
    @NotNull
    @Size(min = 2, max = 50)
    private String firstName;

    @NotNull
    @Size(min = 2, max = 50)
    private String lastName;

    @NotNull
    @Size(min = 2, max = 100)
    private String address;

    @NotNull
    @Size(min = 11, max = 11)
    private String phoneNumber;

    @NotNull
    @Size(min = 5, max = 50)
    private String email;

    @NotNull
    @Size(min = 10, max = 500)
    private String message;
}
