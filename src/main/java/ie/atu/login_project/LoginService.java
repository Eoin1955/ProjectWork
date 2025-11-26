package ie.atu.login_project;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {
    private LoginRepository Repo;
    public LoginService(LoginRepository Repo) {
        this.Repo = Repo;
    }

    public Login createLogin(Login login) {
        return Repo.save(login);
    }

    public List<Login> getAllLogins() {return Repo.findAll();}

    public Login getLoginById(Long id) {return Repo.findById(id).orElseThrow(()->new IllegalArgumentException("Login id not found"));}

    public Login updateLogin(Long id, Login login) {
        Login existing = Repo.findById(id).orElseThrow(()->new IllegalArgumentException("Login id not found"));

        existing.setUsername(login.getUsername());
        existing.setPassword(login.getPassword());
        return Repo.save(existing);
    }

    public Login deleteLogin(Long id) {
        Login login = Repo.findById(id).orElseThrow(()->new IllegalArgumentException("Login id not found"));
        Repo.delete(login);
        return login;
    }
}
