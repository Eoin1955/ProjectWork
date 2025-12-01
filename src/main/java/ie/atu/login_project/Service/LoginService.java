package ie.atu.login_project.Service;

import ie.atu.login_project.Repository.LoginRepository;
import ie.atu.login_project.Model.Login;
import ie.atu.login_project.Model.PersonDetails;
import ie.atu.login_project.Repository.PersonDetailsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {
    private LoginRepository Repo;
    private PersonDetailsRepository PDRepo;

    public LoginService(LoginRepository Repo, PersonDetailsRepository PDRepo) {
        this.Repo = Repo;
        this.PDRepo=PDRepo;
    }

    //Login
    public Login createLogin(Login login) {
        return Repo.save(login);
    }

    public List<Login> getAllLogins() {return Repo.findAll();}

    public Login getLoginById(Long loginId) {return Repo.findById(loginId).orElseThrow(()->new IllegalArgumentException("Login id not found"));}

    public Login updateLogin(Long loginId ,Login login) {
        Login existing = Repo.findById(loginId).orElseThrow(()->new IllegalArgumentException("Login id not found"));

        existing.setUsername(login.getUsername());
        existing.setPassword(login.getPassword());
        existing.setAdmin(login.getAdmin());
        return Repo.save(existing);
    }

    public Login deleteLogin(Long loginId) {
        Login login = Repo.findById(loginId).orElseThrow(()->new IllegalArgumentException("Login id not found"));
        Repo.delete(login);
        return login;
    }

    //Details
    public PersonDetails createPersonDetails(Long loginId, PersonDetails personDetails) {

       Login login = Repo.findById(loginId).orElseThrow(()->new IllegalArgumentException("Login id not found"));

        personDetails.setLogin(login);
        login.setPersonDetails(personDetails);

        return PDRepo.save(personDetails);
    }

    public List<PersonDetails> getAllPersonDetails() {
        return PDRepo.findAll();
    }

    public PersonDetails updatePersonDetails(Long loginId,PersonDetails personDetails) {
        PersonDetails existing = PDRepo.findById(loginId).orElseThrow(()->new IllegalArgumentException("Person Id not found"));

        existing.setFirstName(personDetails.getFirstName());
        existing.setLastName(personDetails.getLastName());
        existing.setEmail(personDetails.getEmail());
        existing.setPhone(personDetails.getPhone());
        existing.setAddress(personDetails.getAddress());
        return PDRepo.save(existing);

    }

    public PersonDetails deletePersonDetails(Long loginId) {
        PersonDetails personDetails= PDRepo.findById(loginId).orElseThrow(()->new IllegalArgumentException("Login id not found"));
        PDRepo.delete(personDetails);
        return personDetails;
    }
}
