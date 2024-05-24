package codewizards.heal_trip.business.DTOs.requests.authentication;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthenticationRequest {
    @NotNull
    @Size(min = 3, max = 50)
    private String email;
    @NotNull
    @Size(min = 3, max = 50)
    private String password;
}
