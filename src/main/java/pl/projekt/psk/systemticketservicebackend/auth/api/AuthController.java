package pl.projekt.psk.systemticketservicebackend.auth.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.projekt.psk.systemticketservicebackend.auth.JwtUtil;
import pl.projekt.psk.systemticketservicebackend.auth.UserRepository;
import pl.projekt.psk.systemticketservicebackend.auth.dto.AuthRequest;
import pl.projekt.psk.systemticketservicebackend.auth.dto.AuthResponse;
import pl.projekt.psk.systemticketservicebackend.auth.exceptions.UserNotFoundException;
import pl.projekt.psk.systemticketservicebackend.auth.model.User;

import java.security.Principal;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRequest request) {
        if (userRepository.findByUsername(request.username()).isPresent()) {
            return ResponseEntity.badRequest().body("User already exists");
        }
        User user = new User(request.username(), passwordEncoder.encode(request.password()));
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        User user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        if (passwordEncoder.matches(request.password(), user.getPassword())) {
            String token = jwtUtil.generateToken(user.getUsername(), user.getRole());
            return ResponseEntity.ok(new AuthResponse(token));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/grant") // todo unsafe
    public ResponseEntity<String> grant(Principal principal) {
        Optional<User> byUsername = userRepository.findByUsername(principal.getName());
        if (byUsername.isPresent()) {
            User user = byUsername.get();
            user.setRole("ROLE_TECH");
            userRepository.save(user);
            return ResponseEntity.ok("Role granted successfully. Please login again!!!");
        } else {
            return ResponseEntity.badRequest().body("User not found");
        }
    }
}
