package ie.atu.login_project.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;


@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loginId;
    @NotBlank
    @Size(min = 1, max = 20, message = "larger than 20 characters or blank")
    private String username;
    @Size(min = 1, max = 20, message = "larger than 20 characters or blank")
    private String password;

    private Boolean admin;

    @OneToOne(mappedBy = "login", cascade = CascadeType.ALL)
    private PersonDetails personDetails;

    public Login(String username, String password, Boolean admin, PersonDetails personDetails) {
        this.username = username;
        this.password = password;
        this.admin = admin;
        this.personDetails = personDetails;
    }
}
