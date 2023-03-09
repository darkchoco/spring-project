package darkchoco.reactspring.service;

import darkchoco.reactspring.domain.SiteUser;
import darkchoco.reactspring.domain.SiteUserRepository;
import darkchoco.reactspring.web.dto.AuthRequest;
import darkchoco.reactspring.web.dto.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;

    private final SiteUserRepository siteUserRepository;

    private final JwtService jwtService;

    public AuthResponse auth(AuthRequest req) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getUsername(),
                        req.getPassword()
                )
        );

        SiteUser siteUser = siteUserRepository.findByUsername(req.getUsername())
                .orElseThrow();

        return AuthResponse.builder()
                .token(jwtService.generateToken(siteUser))
                .build();
    }
}
