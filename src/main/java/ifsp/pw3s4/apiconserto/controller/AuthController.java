package ifsp.pw3s4.apiconserto.controller;

import ifsp.pw3s4.apiconserto.service.TokenService;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired private AuthenticationManager authManager;
    @Autowired private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody @Validated LoginRequest req) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getLogin(), req.getSenha()
                )
        );
        String jwt = tokenService.gerarToken(auth.getName());
        return ResponseEntity.ok(new LoginResponse(jwt));
    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    static class LoginRequest {
        @NotBlank private String login;
        @NotBlank private String senha;
    }

    @Getter @AllArgsConstructor
    static class LoginResponse {
        private String token;
    }
}