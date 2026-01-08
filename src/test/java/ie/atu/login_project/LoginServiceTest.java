package ie.atu.login_project;

import ie.atu.login_project.ErrorHandling.PersonNotFound;
import ie.atu.login_project.FeignClient.PaymentClient;
import ie.atu.login_project.Model.Login;
import ie.atu.login_project.Model.PersonDetails;
import ie.atu.login_project.Repository.LoginRepository;
import ie.atu.login_project.Repository.PersonDetailsRepository;
import ie.atu.login_project.Service.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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
        Login login = new Login(1L, "eoin", "plasma", false);
        Login savedlogin = new Login(1L,"eoin", "plasma", false);

        when(loginRepository.save(login)).thenReturn(savedlogin);

        Login result = loginService.createLogin(login);

        assertNotNull(result.getLoginId());
        assertEquals("eoin", result.getUsername());

        verify(loginRepository, times(1)).save(login);
    }

    @Test
    void getLoginById_success() {
        Login login = new Login("eoin", "john", true);
        login.setLoginId(1L);
        when(loginRepository.findById(1L)).thenReturn(Optional.of(login));

        Optional<Login> result = loginService.getLoginById(1L);

        assertTrue(result.isPresent());
        assertEquals("eoin", result.get().getUsername());
        verify(loginRepository).findById(1L);
    }

    @Test
    void getLoginById_notFound() {
        when(loginRepository.findById(999L)).thenReturn(Optional.empty());

        PersonNotFound exception = assertThrows(PersonNotFound.class,
                () -> loginService.getLoginById(999L));
        assertEquals("Login with id 999 not found", exception.getMessage());

        verify(loginRepository).findById(999L);
    }

    @Test
    void updateLogin_success() {
        Login login = new Login("eoin", "john", true);
        login.setLoginId(1L);
        Login updatedLogin = new Login("newUser", "newPass", false);
        when(loginRepository.findById(1L)).thenReturn(Optional.of(login));
        when(loginRepository.save(login)).thenReturn(login);

        Optional<Login> result = loginService.updateLogin(1L, updatedLogin);

        assertTrue(result.isPresent());
        assertEquals("newUser", result.get().getUsername());
        assertEquals("newPass", result.get().getPassword());

        verify(loginRepository).findById(1L);
        verify(loginRepository).save(login);
    }

    @Test
    void updateLogin_notFound() {
        Login login = new Login("eoin", "john", true);
        login.setLoginId(1L);
        when(loginRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(PersonNotFound.class, () -> loginService.updateLogin(999L, login));

        verify(loginRepository).findById(999L);
        verify(loginRepository, never()).save(any());
    }

    @Test
    void deleteLogin_success() {
        Login login = new Login("eoin", "john", true);
        login.setLoginId(1L);
        when(loginRepository.findById(1L)).thenReturn(Optional.of(login));

        Optional<Login> result = loginService.deleteLogin(1L);

        assertTrue(result.isPresent());
        verify(loginRepository).findById(1L);
        verify(loginRepository).delete(login);
    }

    @Test
    void deleteLogin_notFound() {
        when(loginRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(PersonNotFound.class, () -> loginService.deleteLogin(999L));

        verify(loginRepository).findById(999L);
        verify(loginRepository, never()).delete(any());
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
        assertEquals("Ager", result.get().getFirstName());

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
