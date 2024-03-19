package emtlab.mk.finki.ukim.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;
    @Column(unique = true)
    private String username;

    private String password;

    private Role role;
    public AppUser(String name, String surname, String username, String password) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.username = username;
        role = Role.USER;
    }
}
