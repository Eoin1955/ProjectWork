package ie.atu.login_project;

import ie.atu.login_project.ErrorHandling.PersonNotFound;
import ie.atu.login_project.FeignClient.PaymentClient;
import ie.atu.login_project.Model.Login;
import ie.atu.login_project.Repository.LoginRepository;
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
    void updatePayment() {
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
    void updatePayment_notFound() {
        Login existing = new Login("eoin", "plasma", false);

        when(loginRepository.findByUsername("eoin")).thenReturn(Optional.of(existing));

        assertThrows(PersonNotFound.class, ()-> loginService.updateLogin(1L, existing));

        verify(loginRepository, times(0)).save(existing);
    }

    @Test
    void deletePayment() {
        Login existing = new Login("eoin", "plasma", false);

        when(loginRepository.findByUsername("eoin")).thenReturn(Optional.of(existing));

        Optional<Login> result = loginService.deleteLogin(1L);

        assertTrue(result.isPresent());

        verify(loginRepository,times(1)).delete(existing);
    }

    @Test
    void deletePayment_notFound() {
        Login existing = new Login("eoin", "plasma", false);

        when(loginRepository.findByUsername("eoin")).thenReturn(Optional.empty());

        assertThrows(PersonNotFound.class, ()-> loginService.deleteLogin(1L));

        verify(loginRepository, times(0)).delete(existing);
    }

    @Mock
    private LoginRepository loginRepository;
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
    void updatePayment() {
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
    void updatePayment_notFound() {
        Login existing = new Login("eoin", "plasma", false);

        when(loginRepository.findByUsername("eoin")).thenReturn(Optional.of(existing));

        assertThrows(PersonNotFound.class, ()-> loginService.updateLogin(1L, existing));

        verify(loginRepository, times(0)).save(existing);
    }

    @Test
    void deletePayment() {
        Login existing = new Login("eoin", "plasma", false);

        when(loginRepository.findByUsername("eoin")).thenReturn(Optional.of(existing));

        Optional<Login> result = loginService.deleteLogin(1L);

        assertTrue(result.isPresent());

        verify(loginRepository,times(1)).delete(existing);
    }

    @Test
    void deletePayment_notFound() {
        Login existing = new Login("eoin", "plasma", false);

        when(loginRepository.findByUsername("eoin")).thenReturn(Optional.empty());

        assertThrows(PersonNotFound.class, ()-> loginService.deleteLogin(1L));

        verify(loginRepository, times(0)).delete(existing);
    }

}
