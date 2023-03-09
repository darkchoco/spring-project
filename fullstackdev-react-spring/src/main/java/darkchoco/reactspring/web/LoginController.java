package darkchoco.reactspring.web;

import darkchoco.reactspring.service.AuthService;
import darkchoco.reactspring.web.dto.AuthRequest;
import darkchoco.reactspring.web.dto.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class LoginController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest req) {
        return ResponseEntity.ok(authService.auth(req));
	}
}
