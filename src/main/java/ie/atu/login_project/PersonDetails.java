package ie.atu.login_project;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonDetails {
    private  String username;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private String address;
    private String phone;
}
