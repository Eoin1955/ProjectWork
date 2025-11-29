package ie.atu.login_project.Controller;

import ie.atu.login_project.Model.Login;
import ie.atu.login_project.Model.PersonDetails;
import ie.atu.login_project.Service.LoginService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Login registerUser(@Valid @RequestBody Login registerLogin) {
       return loginService.createLogin(registerLogin);
    }

    @GetMapping("/loginUser")
    public Login LoginUser(Long Loginid) {
      return loginService.getLoginById(Loginid);
    }

    @GetMapping
    public List<Login> GetUser(){
        return loginService.getAllLogins();
    }

    @PutMapping("/update")
    public Login updateUser(@PathVariable Long Loginid, @Valid @RequestBody Login updateLogin) {
        return loginService.updateLogin(Loginid, updateLogin);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@RequestParam Long Loginid) {
        loginService.deleteLogin(Loginid);
    }

   @PostMapping("/PersonDetails")
    @ResponseStatus(HttpStatus.CREATED)
    public PersonDetails createPersonDetails(@PathVariable Long loginId, @Valid @RequestBody  PersonDetails personDetails) {
       return loginService.createPersonDetails(loginId, personDetails);
    }

    @GetMapping
    public List<PersonDetails> GetPersonDetails() {
        return loginService.getAllPersonDetails();
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePersonDetails(@RequestParam Long loginid) {
        loginService.deleteLogin(loginid);
    }
}
