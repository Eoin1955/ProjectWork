package ie.atu.login_project;

import ie.atu.login_project.Model.Login;
import ie.atu.login_project.Model.PersonDetails;
import ie.atu.login_project.Repository.LoginRepository;
import ie.atu.login_project.Repository.PersonDetailsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final LoginRepository loginRepository;

    public DataLoader(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        PersonDetails personDetails1 = new PersonDetails("Eoin", "Ager", 20, "g00431577@atu.ie", "Kilmaine", "123456789");
        Login login1 = new Login("Eoin", "plasma", true,  personDetails1);

        PersonDetails personDetails2 = new PersonDetails("Lance", "Cruz", 21, "g00547679@atu.ie", "Clonbur", "123456789");
        Login login2 = new Login("Lance", "minecraft", false,  personDetails2);

        PersonDetails personDetails3 = new PersonDetails("Anzelina", "Harevica", 22, "g00676767@atu.ie", "Galway", "123456789");
        Login login3 = new Login("Anz", "val", false,   personDetails3);


     loginRepository.save(login1);
     loginRepository.save(login2);
     loginRepository.save(login3);
    }
}
