package codewizards.heal_trip.core.security;

import org.springframework.security.core.userdetails.UserDetails;

public interface CustomUserDetails extends UserDetails {
    String getName();

    int getId();
}
