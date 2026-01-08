package ie.atu.login_project;

import com.fasterxml.jackson.databind.ObjectMapper;
import ie.atu.login_project.Controller.LoginController;
import ie.atu.login_project.DTO.BookDTO;
import ie.atu.login_project.DTO.PaymentDTO;
import ie.atu.login_project.FeignClient.BookClient;
import ie.atu.login_project.FeignClient.PaymentClient;
import ie.atu.login_project.Model.Login;
import ie.atu.login_project.Model.PersonDetails;
import ie.atu.login_project.Service.LoginService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LoginController.class)
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private LoginService loginService;

    @MockitoBean
    private BookClient bookClient;

    @MockitoBean
    private PaymentClient paymentClient;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testRegisterUser() throws Exception {
        Login login = new Login("eoin", "john", true);
        when(loginService.createLogin(any(Login.class))).thenReturn(login);

        String json = mapper.writeValueAsString(login);

        mockMvc.perform(post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value("john"));
    }

    @Test
    public void testGetUserById() throws Exception {
        Login login = new Login("eoin", "john", true);
        when(loginService.getLoginById(1L)).thenReturn(Optional.of(login));

        mockMvc.perform(get("/api/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testGetUserByIdNotFound() throws Exception {
        when(loginService.getLoginById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/user/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetAllUsers() throws Exception {
        List<Login> users = List.of(
                new Login("eoin", "john", true),
                new Login("anz", "ann", true)
        );
        when(loginService.getAllLogins()).thenReturn(users);

        mockMvc.perform(get("/api/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void testUpdateUser() throws Exception {
        Login login = new Login("eoin", "john", true);
        when(loginService.getLoginById(1L)).thenReturn(Optional.of(login));

        String json = mapper.writeValueAsString(login);

        mockMvc.perform(put("/api/user/update/1?Loginid=1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateUserNotFound() throws Exception {
        when(loginService.getLoginById(999L)).thenReturn(Optional.empty());

        Login login = new Login("eoin", "john", true);
        String json = mapper.writeValueAsString(login);

        mockMvc.perform(put("/api/user/update/999?Loginid=999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteUser() throws Exception {
        Login login = new Login("eoin", "john", true);
        when(loginService.getLoginById(1L)).thenReturn(Optional.of(login));

        mockMvc.perform(delete("/api/user/1?Loginid=1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteUserNotFound() throws Exception {
        when(loginService.getLoginById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/user/999?Loginid=999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreatePersonDetails() throws Exception {
        PersonDetails details = new PersonDetails();
        details.setLoginId(1L);
        details.setFirstName("John");

        when(loginService.createPersonDetails(anyLong(), any(PersonDetails.class)))
                .thenReturn(details);

        String json = mapper.writeValueAsString(details);

        mockMvc.perform(post("/api/user/PersonDetails/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("John"));
    }

    @Test
    public void testGetAllPersonDetails() throws Exception {
        List<PersonDetails> details = List.of(
                new PersonDetails("John", "Doe", 18, "john@atu.ie","123 St", "0892397469"),
                new PersonDetails("jane", "bow", 20, "jane@atu.ie","123 Place", "0892784469")
        );
        when(loginService.getAllPersonDetails()).thenReturn(details);

        mockMvc.perform(get("/api/user/person"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void testDeletePersonDetails() throws Exception {
        Login login = new Login("eoin", "john", true);
        when(loginService.getLoginById(1L)).thenReturn(Optional.of(login));

        mockMvc.perform(delete("/api/user/person/1?loginid=1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDeletePersonDetailsNotFound() throws Exception {
        when(loginService.getLoginById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/user/person/1?loginid=999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdatePersonDetails() throws Exception {
        Login login = new Login("eoin", "john", true);
        PersonDetails details = new PersonDetails("John", "Doe", 18, "john@atu.ie","123 St", "0892397469");
        login.setPersonDetails(details);

        when(loginService.getLoginById(1L)).thenReturn(Optional.of(login));

        String json = mapper.writeValueAsString(details);

        mockMvc.perform(put("/api/user/updateDetails/1?loginid=1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdatePersonDetailsNotFound() throws Exception {
        when(loginService.getLoginById(999L)).thenReturn(Optional.empty());

        PersonDetails details = new PersonDetails("John", "Doe", 18, "john@atu.ie","123 St", "0892397469");
        String json = mapper.writeValueAsString(details);

        mockMvc.perform(put("/api/user/updateDetails/1?loginid=999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetBook() throws Exception {
        BookDTO book = new BookDTO(1L, "Garry Potter", "JK Rowling", "2024", 40.0);
        when(bookClient.getBookID(1L)).thenReturn(book);

        mockMvc.perform(get("/api/user/GetBook/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testGetPayment() throws Exception {
        PaymentDTO payment = new PaymentDTO(1L, "100", "Cash", "Euro");
        when(paymentClient.getPaymentID(1L)).thenReturn(payment);

        mockMvc.perform(get("/api/user/payment-history/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }
}