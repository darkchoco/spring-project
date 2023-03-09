package darkchoco.springboot3securityjwt.service;

import darkchoco.springboot3securityjwt.domain.Role;
import darkchoco.springboot3securityjwt.domain.User;
import darkchoco.springboot3securityjwt.domain.UserRepository;
import darkchoco.springboot3securityjwt.web.dto.AuthRequest;
import darkchoco.springboot3securityjwt.web.dto.AuthResponse;
import darkchoco.springboot3securityjwt.web.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest req) {
        User user = User.builder()
                .firstname(req.getFirstname())
                .lastname(req.getLastname())
                .email(req.getEmail())
                .password(new BCryptPasswordEncoder().encode(req.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.generateToken(user))  // public class User implements UserDetails ...
                .build();
    }

    public AuthResponse auth(AuthRequest req) {
        // Spring Security에서 제공하는 AuthenticationManager.authenticate() 를 이용하여
        // username(여기서는 email)과 password가 맞는지 확인한다.
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getEmail(),
                        req.getPassword()
                )
        );

        // username과 password가 맞으니(그렇지 않으면 바로 위에서 이미 Exception 발생) Token을 생성한다
        User user = userRepository.findByEmail(req.getEmail())
                .orElseThrow();

        return AuthResponse.builder()
                .token(jwtService.generateToken(user))  // public class User implements UserDetails ...
                .build();
    }
}
