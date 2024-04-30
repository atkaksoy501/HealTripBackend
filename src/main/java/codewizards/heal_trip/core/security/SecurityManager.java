package codewizards.heal_trip.core.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Service;

@Service
public class SecurityManager implements SecurityService {
    private static final String[] WHITE_LIST_URLS = {
            "/auth/authenticate",
            "/auth/register",
            "/auth/register/patient",
            "/department/getAll",
            "/department/getAllSorted",
            "/department/getAllByPage",
            "/retreat/getAll",
            "/retreat/getByDepartmentId/**",
            "/booking/add",
            "/patient/update/**"
    };

    private static final String[] SWAGGER_URLS = {
            "/swagger-ui/**",
            "/v2/api-docs/**",
            "/v3/api-docs/**",
            "/v3/api-docs/**"
    };

    private static final String[] ADMIN_URLS = {
            "/retreat/add",
            "/retreat/update/**",
            "/retreat/delete/**",
            "/patient/add",
            "/patient/delete/**",
            "/hotelOrganizer/add",
            "/hotelOrganizer/update/**",
            "/hotelOrganizer/delete/**",
            "/hospitalOrganizer/add",
            "/hospitalOrganizer/update/**",
            "/hospitalOrganizer/delete/**",
            "/hotel/add",
            "/hotel/update/**",
            "/hotel/delete/**",
            "/hospital/add",
            "/hospital/update/**",
            "/hospital/delete/**",
            "/doctor/add",
            "/doctor/update/**",
            "/doctor/delete/**",
            "/department/add",
            "/department/update/**",
            "/department/delete/**",
            "/image/retreat/save",
            "/image/retreat/delete/**",
            "/image/hotel/save",
            "/image/hotel/delete/**",
            "/image/hospital/save",
            "/image/hospital/delete/**",
            "/email/send",
            "/email/sendWelcome",
            "/email/sendAppointment"
    };

    @Override
    public HttpSecurity configureSecurity(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers(WHITE_LIST_URLS).permitAll()
                        .requestMatchers(SWAGGER_URLS).hasRole(Roles.ADMIN)
                        .requestMatchers(ADMIN_URLS).hasRole(Roles.ADMIN)
                        .requestMatchers(HttpMethod.GET).permitAll()
                        .anyRequest().authenticated()
        );
        return http;
    }
}
