package ie.atu.login_project;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/loginUser")
public class LoginController {
    List<Login> UsernameList = new ArrayList<>();
    List<PersonDetails> PersonList = new ArrayList<>();

    @PostMapping("/RegisterUser")
    public Login registerUser(@Valid @RequestBody Login registerLogin) {
        // Check if username already exists
        for (Login user : UsernameList) {
            if (user.getUsername().equals(registerLogin.getUsername())) {
                return new Login(registerLogin.getUsername(), "Username already exists");
            }
        }

        // Add new user if username not found
        UsernameList.add(registerLogin);
        return new Login(registerLogin.getUsername(), "Registration successful");
    }

    @GetMapping("/loginUser")
    public Login LoginUser(@Valid @RequestParam String username, @RequestParam String password) {
        for (Login login : UsernameList) {
            if(login.getUsername().equals(username) && login.getPassword().equals(password)) {
                return new Login(username, "successful login");
            }
        }
        return new Login(username, "failed login");
    }

    @GetMapping("/GetUser")
    public List<Login> GetUser(){
        new Login("Username", "Password");
        return UsernameList;
    }

    @PostMapping("/MoreDetails")
    public PersonDetails MoreDetails(@Valid @RequestBody PersonDetails personDetails) {
        for(Login login : UsernameList) {
            if(login.getUsername().equals(personDetails.getUsername())) {
                for (PersonDetails personDetail : PersonList) {
                    if (personDetail.getUsername().equals(personDetails.getUsername())) {
                        return new PersonDetails(personDetail.getUsername(),"Username Details already entered","",0,"","","");
                    }
                }

                PersonList.add(personDetails);
                return personDetails;
            }
            }

        return new PersonDetails("","Username isn't registered","",0,"","","");
    }

    @GetMapping("/MoreDetails")
    public List<PersonDetails> MoreDetails(){
        new PersonDetails("username","FirstName","LastName",0,"Email","Address","Phone");
        return PersonList;
    }

    @GetMapping("/Size")
    public int Size(){
        return PersonList.size();
    }
}
