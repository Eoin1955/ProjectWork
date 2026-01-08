package ie.atu.login_project;

import ie.atu.login_project.ErrorHandling.PersonNotFound;
import ie.atu.login_project.FeignClient.PaymentClient;
import ie.atu.login_project.Model.Login;
import ie.atu.login_project.Model.PersonDetails;
import ie.atu.login_project.Repository.LoginRepository;
import ie.atu.login_project.Repository.PersonDetailsRepository;
import ie.atu.login_project.Service.LoginService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LoginServiceTest {

    @Mock
    private LoginRepository loginRepository;

    @Mock
    private PersonDetailsRepository personRepository;

    @InjectMocks
    private LoginService loginService;

    @Test
    void createLogin() {
        Login login = new Login("eoin", "plasma", false);
        Login savedlogin = new Login("eoin", "plasma", false);

        when(loginRepository.save(login)).thenReturn(savedlogin);

        Login result = loginService.createLogin(login);

        assertNotNull(result.getLoginId());
        assertEquals("eoin", result.getUsername());

        verify(loginRepository, times(1)).save(login);
    }

    @Test
    void getLogin() {
        Login savedlogin = new Login("eoin", "plasma", false);

        when(loginRepository.findByUsername("eoin")).thenReturn(Optional.of(savedlogin));

        Optional<Login> result = loginService.getLoginById(1L);

        assertTrue(result.isPresent());
        assertEquals("plasma", result.get().getPassword());

        verify(loginRepository, times(1)).findByUsername("eoin");
    }

    @Test
    void getLogin_notFound() {
        when(loginRepository.findByUsername("eoin")).thenReturn(Optional.empty());

        assertThrows(PersonNotFound.class, ()-> loginService.getLoginById(1L));

        verify(loginRepository, times(1)).findByUsername("eoin");
    }

    @Test
    void updateLogin() {
        Login existing = new Login("eoin", "plasma", false);
        Login updated = new Login("eoin", "plasma", false);

        when(loginRepository.findByUsername("eoin")).thenReturn(Optional.of(existing));
        when(loginRepository.save(any(Login.class))).thenReturn(updated);

        Optional<Login> result = loginService.updateLogin(1L, updated);

        assertTrue(result.isPresent());
        assertEquals("eoin", result.get().getUsername());

        verify(loginRepository, times(1)).save(existing);
    }

    @Test
    void updateLogin_notFound() {
        Login existing = new Login("eoin", "plasma", false);

        when(loginRepository.findByUsername("eoin")).thenReturn(Optional.of(existing));

        assertThrows(PersonNotFound.class, ()-> loginService.updateLogin(1L, existing));

        verify(loginRepository, times(0)).save(existing);
    }

    @Test
    void deleteLogin() {
        Login existing = new Login("eoin", "plasma", false);

        when(loginRepository.findByUsername("eoin")).thenReturn(Optional.of(existing));

        Optional<Login> result = loginService.deleteLogin(1L);

        assertTrue(result.isPresent());

        verify(loginRepository,times(1)).delete(existing);
    }

    @Test
    void deleteLogin_notFound() {
        Login existing = new Login("eoin", "plasma", false);

        when(loginRepository.findByUsername("eoin")).thenReturn(Optional.empty());

        assertThrows(PersonNotFound.class, ()-> loginService.deleteLogin(1L));

        verify(loginRepository, times(0)).delete(existing);
    }

    //Person detail tests
    @Test
    void createPersonDetails_success() {

        Long loginId = 1L;
        Login login = new Login();
        login.setLoginId(loginId);

        PersonDetails person = new PersonDetails(
                "Eoin", "Ager", 20,
                "G00431577@atu.ie",
                "Kilmaine Mayo",
                "0899278398"
        );

        when(loginRepository.findById(loginId)).thenReturn(Optional.of(login));
        when(personRepository.save(person)).thenReturn(person);

        PersonDetails result = loginService.createPersonDetails(loginId, person);

        assertNotNull(result);
        assertEquals(login, result.getLogin());

        verify(loginRepository).findById(loginId);
        verify(personRepository).save(person);
    }

    @Test
    void createPersonDetails_loginNotFound() {

        when(loginRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(PersonNotFound.class, () -> loginService.createPersonDetails(1L, new PersonDetails())
        );

        verify(personRepository, never()).save(any());
    }

    @Test
    void updatePersonDetails_success() {

        Long id = 1L;

        PersonDetails existing = new PersonDetails();
        existing.setFirstName("Old");

        PersonDetails updated = new PersonDetails("Ager", "Eoin", 21,
                "eoinager@gmail.com",
                "Knocknacarra Galway",
                "0899278398"
        );

        when(personRepository.findById(id)).thenReturn(Optional.of(existing));
        when(personRepository.save(existing)).thenReturn(existing);

        Optional<PersonDetails> result = loginService.updatePersonDetails(id, updated);

        assertTrue(result.isPresent());
        assertEquals("New", result.get().getFirstName());

        verify(personRepository).findById(id);
        verify(personRepository).save(existing);
    }


    @Test
    void updatePersonDetails_notFound() {

        when(personRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(PersonNotFound.class, () -> loginService.updatePersonDetails(1L, new PersonDetails())
        );

        verify(personRepository, never()).save(any());
    }


    @Test
    void deletePersonDetails_success() {

        Long id = 1L;
        PersonDetails person = new PersonDetails();

        when(personRepository.findById(id)).thenReturn(Optional.of(person));

        Optional<PersonDetails> result = loginService.deletePersonDetails(id);

        assertTrue(result.isPresent());

        verify(personRepository).findById(id);
        verify(personRepository).delete(person);
    }


    @Test
    void deletePersonDetails_notFound() {

        when(personRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(PersonNotFound.class, () -> loginService.deletePersonDetails(1L)
        );

        verify(personRepository, never()).delete(any());
    }

}
