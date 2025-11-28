package ie.atu.login_project.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonDetails {
    @Id
    private String Loginid;

    @OneToOne
    @MapsId
    @JoinColumn(name = "personId")
    private Login login;

    @Size(min = 1, max = 50, message = "firstname more than 50 characters or blank")
    private String firstName;
    @Size(min = 1, max = 50, message = "lastname more than 50 characters or blank")
    private String lastName;
    @Positive(message = "age must be positive")
    @NotNull(message = "age cant be 0")
    private int age;
    @NotBlank(message = "email blank")
    @Email(message = "please enter valid email")
    private String email;
    @NotBlank(message = "address blank")
    private String address;
    @Size(min = 9, max = 10, message = "phone number should be between 9 and 10 characters")
    private String phone;
}
