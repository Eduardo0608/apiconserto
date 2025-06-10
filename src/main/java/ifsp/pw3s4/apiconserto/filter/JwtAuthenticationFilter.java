package ifsp.pw3s4.apiconserto.filter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import ifsp.pw3s4.apiconserto.service.AutenticacaoService;
import ifsp.pw3s4.apiconserto.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired private TokenService tokenService;
    @Autowired private AutenticacaoService authService;

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain)
            throws ServletException, IOException {
        String header = req.getHeader("Authorization");
        if (StringUtils.hasText(header) && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            try {
                String login = tokenService.getSubject(token);
                var userDetails = authService.loadUserByUsername(login);
                var auth = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            } catch (JWTVerificationException ex) {
            }
        }
        chain.doFilter(req, res);
    }
}