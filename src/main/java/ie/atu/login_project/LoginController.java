package ie.atu.login_project;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginController {
    private String username;
    private String password;
}
