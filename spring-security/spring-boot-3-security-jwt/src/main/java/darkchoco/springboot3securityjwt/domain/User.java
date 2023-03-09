package darkchoco.springboot3securityjwt.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "appuser")  // 'user' is reserved in PostgreSQL so another name should be assigned
public class User implements UserDetails {

    @Id
    @GeneratedValue  // GenerationType.AUTO is default
    private Integer id;

    private String firstname;

    private String lastname;

    private String email;

    private String password;  // getPassword()는 Lombok을 통해 자동으로 처리.
                              // IntelliJ > Structure window 에서 확인 가능하다.

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;  // false로 하면 영원히 connect 못한다
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
