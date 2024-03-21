package codewizards.heal_trip.business.DTOs.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddedAddressResponse {
    private int id;
    private String country;
    private String city;
    private String addressDetail;
    private LocalDateTime createdDate;
}
