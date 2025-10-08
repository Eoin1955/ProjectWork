package ie.atu.login_project;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/loginUser")
public class LoginController {
    List<Login> UsernameList = new ArrayList<>();

    @PostMapping("/RegisterUser")
    public Login RegisterUser(@RequestBody Login Registerlogin) {
        UsernameList.add(Registerlogin);
        return (Login) UsernameList;
    }

    @GetMapping("/loginUser")
    public Login LoginUser(@RequestParam String username, @RequestParam String password) {
        for (Login login : UsernameList) {
            if(login.getUsername().equals(username) && login.getPassword().equals(password)) {
                return new Login(username, "successful login");
            }
        }
        return new Login(username, "failed login");
    }

    @GetMapping("/GetUser")
    public List<Login> GetUser(){
        Login login = new Login("Username", "Password");
        return UsernameList;
    }
}
