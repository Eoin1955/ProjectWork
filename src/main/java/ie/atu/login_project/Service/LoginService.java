package ie.atu.login_project.Service;

import ie.atu.login_project.ErrorHandling.PersonNotFound;
import ie.atu.login_project.Repository.LoginRepository;
import ie.atu.login_project.Model.Login;
import ie.atu.login_project.Model.PersonDetails;
import ie.atu.login_project.Repository.PersonDetailsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

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

    public Optional<Login> getLoginById(Long loginId) {
        Optional<Login> login = Repo.findById(loginId);
        if(login.isPresent()) {
            return login;
        }
        else {
            throw new PersonNotFound("Login with id " + loginId + " not found");
        }
    }

    public Optional<Login> updateLogin(Long loginId ,Login login) {
        Optional<Login> maybe = Repo.findById(loginId);
        if(maybe.isPresent()) {
            Login existing = maybe.get();
            existing.setUsername(login.getUsername());
            existing.setPassword(login.getPassword());
            existing.setAdmin(login.getAdmin());
            Repo.save(existing);
            return Optional.of(existing);
        }
        else {
            throw new  PersonNotFound("Login with id " + loginId + " not found");
        }
    }

    public Optional<Login> deleteLogin(Long loginId) {
        Optional<Login> maybe = Repo.findById(loginId);
        if(maybe.isPresent()) {
            Repo.delete(maybe.get());
            return maybe;
        }
        else{
            throw new  PersonNotFound("Login with id " + loginId + " not found");
        }
    }

    //Details
    public PersonDetails createPersonDetails(Long loginId, PersonDetails personDetails) {

        Login login = Repo.findById(loginId).orElseThrow(()->new IllegalArgumentException("Login id not found"));

        personDetails.setLogin(login);

        return PDRepo.save(personDetails);
    }

    public List<PersonDetails> getAllPersonDetails() {
        return PDRepo.findAll();
    }

    public Optional<PersonDetails> updatePersonDetails(Long loginId,PersonDetails personDetails) {
        Optional<PersonDetails> maybe = PDRepo.findById(loginId);
        if(maybe.isPresent()) {
            PersonDetails existing = maybe.get();
            existing.setFirstName(personDetails.getFirstName());
            existing.setLastName(personDetails.getLastName());
            existing.setEmail(personDetails.getEmail());
            existing.setPhone(personDetails.getPhone());
            existing.setAddress(personDetails.getAddress());
            PDRepo.save(existing);
            return Optional.of(existing);
        }
        else {
            throw new  PersonNotFound("Login with id " + loginId + " not found");
        }
    }

    public Optional<PersonDetails> deletePersonDetails(Long loginId) {
        Optional<PersonDetails> personDetails= PDRepo.findById(loginId);
        if(personDetails.isPresent()) {
            PDRepo.delete(personDetails.get());
            return personDetails;
        }
       else {
           throw new  PersonNotFound("Login with id " + loginId + " not found");
        }
    }
}
