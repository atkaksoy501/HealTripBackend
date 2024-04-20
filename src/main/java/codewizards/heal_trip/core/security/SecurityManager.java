package codewizards.heal_trip.core.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Service;

@Service
public class SecurityManager implements SecurityService {
    private static final String[] WHITE_LIST_URLS = {
            "/swagger-ui/**",
            "/v2/api-docs/**",
            "/v3/api-docs/**",
            "/v3/api-docs/**",
            "/auth/authenticate",
            "/auth/register",
            "/auth/register/patient",
            "/department/getAll",
            "/department/getAllSorted",
            "/department/getAllByPage",
            "/retreat/getAll",
            "/retreat/getByDepartmentId/**"
    };

    @Override
    public HttpSecurity configureSecurity(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers(WHITE_LIST_URLS).permitAll()
//                        .requestMatchers("/swagger-ui/index.html").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET).permitAll()
                        .anyRequest().authenticated()
        );
        return http;
    }
}
