package darkchoco.ss02m.model;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Table(name = "customer")
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String pwd;
    private String role;

    public void changePassword(String newPassword) {
        this.pwd = newPassword;
    }
}
