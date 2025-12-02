package ie.atu.login_project.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonDetails {
    @Id
    private Long loginId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "login_id")
    @JsonBackReference
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

    public PersonDetails() {}

    // Constructor without login
    public PersonDetails(String firstName, String lastName, Integer age, String email,
                         String address, String phone) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }

    // Full constructor (usually not needed)
    public PersonDetails(Long loginId, Login login, String firstName,
                         String lastName, Integer age, String email,
                         String address, String phone) {

        this.loginId = loginId;
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }

    public @Size(min = 1, max = 50, message = "firstname more than 50 characters or blank") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@Size(min = 1, max = 50, message = "firstname more than 50 characters or blank") String firstName) {
        this.firstName = firstName;
    }

    public @Size(min = 1, max = 50, message = "lastname more than 50 characters or blank") String getLastName() {
        return lastName;
    }

    public void setLastName(@Size(min = 1, max = 50, message = "lastname more than 50 characters or blank") String lastName) {
        this.lastName = lastName;
    }

    @Positive(message = "age must be positive")
    @NotNull(message = "age cant be 0")
    public int getAge() {
        return age;
    }

    public void setAge(@Positive(message = "age must be positive") @NotNull(message = "age cant be 0") int age) {
        this.age = age;
    }

    public @NotBlank(message = "email blank") @Email(message = "please enter valid email") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "email blank") @Email(message = "please enter valid email") String email) {
        this.email = email;
    }

    public @NotBlank(message = "address blank") String getAddress() {
        return address;
    }

    public void setAddress(@NotBlank(message = "address blank") String address) {
        this.address = address;
    }

    public @Size(min = 9, max = 10, message = "phone number should be between 9 and 10 characters") String getPhone() {
        return phone;
    }

    public void setPhone(@Size(min = 9, max = 10, message = "phone number should be between 9 and 10 characters") String phone) {
        this.phone = phone;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }
}
