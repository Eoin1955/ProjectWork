package ie.atu.login_project;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonDetails {
    @Size(min = 1, max = 20, message = "larger than 20 characters or blank")
    @NotBlank
    private  String username;
    @Size(min = 1, max = 50, message = "firstname more than 50 characters or blank")
    private String firstName;
    @Size(min = 1, max = 50, message = "lastname more than 50 characters or blank")
    private String lastName;
    @Positive(message = "age must be positive")
    @NotNull(message = "age cant be 0")
    private int age;
    @NotBlank(message = "email blank")
    @Email(message = "please enter valid email")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Invalid email format")
    private String email;
    @NotBlank(message = "address blank")
    private String address;
    @Size(min = 9, max = 10, message = "phone number should be between 9 and 10 characters")
    private String phone;
}
