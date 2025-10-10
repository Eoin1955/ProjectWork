package ie.atu.login_project;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Login {
    @Size(min = 1, max = 20, message = "larger than 20 characters or blank")
    private String username;
    @Size(min = 1, max = 20, message = "larger than 20 characters or blank")
    private String password;
}
