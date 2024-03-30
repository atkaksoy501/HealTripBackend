package codewizards.heal_trip.security.config;

import codewizards.heal_trip.core.services.JwtService;
import codewizards.heal_trip.security.JpaUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JpaUserDetailsService jpaUserDetailsService;
    private final JwtUtils jwtUtils;
    private final JwtService jwtService;

//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
////        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
//        final String userEmail;
//        String jwtToken = null;
//
//
////        if (authHeader == null || !authHeader.startsWith("Bearer")) {
////            filterChain.doFilter(request, response);
////            return;
////        }
//
////        for (Cookie cookie : request.getCookies()) {
////            if (cookie.getName().equals("jwt")) {
////                jwtToken = cookie.getValue();
//////                System.out.println(cookie.getValue());
////            }
////        }
//        if (jwtToken == null) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
////        jwtToken = authHeader.substring(7);
//        userEmail = jwtUtils.extractUsername(jwtToken);
//        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserDetails userDetails = jpaUserDetailsService.loadUserByUsername(userEmail);
//            if (jwtUtils.validateToken(jwtToken, userDetails)) {
//                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
//                        null, userDetails.getAuthorities());
//                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//            }
//        }
//        filterChain.doFilter(request, response);
//    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        // Jwt'yi oku,
        // JWT'yi ben mi yazdım?
        // JWT hala geçerli mi?
        String jwtHeader = request.getHeader("Authorization");

        if(jwtHeader != null && jwtHeader.startsWith("Bearer "))
        {
            String jwt = jwtHeader.substring(7);
            String username = jwtService.extractUser(jwt);

            if(username!=null)
            {
                UserDetails user = jpaUserDetailsService.loadUserByUsername(username);

                if(jwtService.validateToken(jwt, user))
                {
                    // Boilerplate
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    authenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }

        filterChain.doFilter(request,response);
    }
}
