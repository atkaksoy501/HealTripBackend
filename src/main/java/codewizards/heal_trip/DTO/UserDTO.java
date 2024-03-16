package codewizards.heal_trip.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private String roles;
    private String phone_number;
}
