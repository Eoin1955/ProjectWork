package ie.atu.login_project.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;


@Entity
@Table(name = "users")
@Getter
@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
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

    @OneToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "login_id", foreignKey = @ForeignKey(name = "fk_person_details"))
    @JsonManagedReference
    private PersonDetails personDetails;

    public Login() {}

    // Constructor without ID or personDetails
    public Login(String username, String password, Boolean admin) {
        this.username = username;
        this.password = password;
        this.admin = admin;
    }

    // Full constructor
    public Login(Long loginId, String username, String password, Boolean admin) {
        this.loginId = loginId;
        this.username = username;
        this.password = password;
        this.admin = admin;
    }


    public @NotBlank @Size(min = 1, max = 20, message = "larger than 20 characters or blank") String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank @Size(min = 1, max = 20, message = "larger than 20 characters or blank") String username) {
        this.username = username;
    }

    public @Size(min = 1, max = 20, message = "larger than 20 characters or blank") String getPassword() {
        return password;
    }

    public void setPassword(@Size(min = 1, max = 20, message = "larger than 20 characters or blank") String password) {
        this.password = password;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public PersonDetails getPersonDetails() {
        return personDetails;
    }

    public void setPersonDetails(PersonDetails personDetails) {
        this.personDetails = personDetails;
    }
}
