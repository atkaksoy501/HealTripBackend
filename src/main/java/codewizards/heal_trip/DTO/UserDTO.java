package codewizards.heal_trip.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
    @NotNull
    @Size(min = 3, max = 20)
    private String first_name;

    @NotNull
    @Size(min = 3, max = 20)
    private String last_name;

    @NotNull
    @Size(min = 3, max = 50)
    private String email;

    @NotNull
    @Size(min = 3, max = 50)
    private String password;

    private String roles;

    @NotNull
    @Size(min = 11, max = 11)
    private String phone_number;
}
