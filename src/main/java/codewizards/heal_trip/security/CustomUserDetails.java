package codewizards.heal_trip.security;

import org.springframework.security.core.userdetails.UserDetails;

public interface CustomUserDetails extends UserDetails {
    String getName();

    int getId();
}
