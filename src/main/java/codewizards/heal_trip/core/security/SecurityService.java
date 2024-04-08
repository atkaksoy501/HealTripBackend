package codewizards.heal_trip.core.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

public interface SecurityService {
    HttpSecurity configureSecurity(HttpSecurity http) throws Exception;
}
