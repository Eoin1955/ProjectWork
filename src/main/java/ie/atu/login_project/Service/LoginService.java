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

    public Login createLogin(Login login) {
        return Repo.save(login);
    }

    public PersonDetails createPersonDetails(PersonDetails personDetails) {
        return PDRepo.save(personDetails);
    }

    public List<Login> getAllLogins() {return Repo.findAll();}

    public Login getLoginById(Long Loginid) {return Repo.findById(Loginid).orElseThrow(()->new IllegalArgumentException("Login id not found"));}

    public Login updateLogin(Long Loginid , Login login) {
        Login existing = Repo.findById(Loginid).orElseThrow(()->new IllegalArgumentException("Login id not found"));

        existing.setUsername(login.getUsername());
        existing.setPassword(login.getPassword());
        return Repo.save(existing);
    }

    public Login deleteLogin(Long Loginid) {
        Login login = Repo.findById(Loginid).orElseThrow(()->new IllegalArgumentException("Login id not found"));
        Repo.delete(login);
        return login;
    }

    public List<PersonDetails> getAllPersonDetails() {return PDRepo.findAll();}

    public PersonDetails updatePersonDetails(Long Personid, PersonDetails personDetails) {
        PersonDetails existing = PDRepo.findById(Personid).orElseThrow(()->new IllegalArgumentException("Person Id not found"));

        existing.setFirstName(personDetails.getFirstName());
        existing.setLastName(personDetails.getLastName());
        existing.setEmail(personDetails.getEmail());
        existing.setPhone(personDetails.getPhone());
        existing.setAddress(personDetails.getAddress());
        return PDRepo.save(existing);

    }
}
