package ie.atu.login_project;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/loginUser")
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
    public Login LoginUser(Long id) {
      return loginService.getLoginById(id);
    }

    @GetMapping
    public List<Login> GetUser(){
        return loginService.getAllLogins();
    }

    @PutMapping("/{id}")
    public Login updateUser(@PathVariable Long id, @Valid @RequestBody Login updateLogin) {
        return loginService.updateLogin(id, updateLogin);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@RequestParam Long id) {
        loginService.deleteLogin(id);
    }
}
