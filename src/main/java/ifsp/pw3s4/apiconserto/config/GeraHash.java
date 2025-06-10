package ifsp.pw3s4.apiconserto.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeraHash {
    public static void main(String[] args) {
        String raw = "123456";
        String hash = new BCryptPasswordEncoder().encode(raw);
        System.out.println(hash);
    }
}