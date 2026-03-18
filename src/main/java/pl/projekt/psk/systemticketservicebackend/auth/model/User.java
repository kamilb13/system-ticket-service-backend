package pl.projekt.psk.systemticketservicebackend.auth.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "USERNAME", unique = true, nullable = false)
    private String username;
    @Column(name = "PASSWORD", nullable = false)
    private String password;
    @Column(name = "ROLE", nullable = false)
    private String role;

    private static final String DEFAULT_ROLE = "ROLE_CLIENT";

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = DEFAULT_ROLE;
    }
}
