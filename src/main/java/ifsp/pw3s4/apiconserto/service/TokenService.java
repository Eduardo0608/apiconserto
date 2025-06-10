package ifsp.pw3s4.apiconserto.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class TokenService {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expirationMillis;

    public String gerarToken(String login) {
        Date now = new Date();
        Date exp = new Date(now.getTime() + expirationMillis);
        return JWT.create()
                .withSubject(login)
                .withIssuedAt(now)
                .withExpiresAt(exp)
                .sign(Algorithm.HMAC256(secret));
    }

    public String getSubject(String token) {
        return JWT.require(Algorithm.HMAC256(secret))
                .build()
                .verify(token)
                .getSubject();
    }
}