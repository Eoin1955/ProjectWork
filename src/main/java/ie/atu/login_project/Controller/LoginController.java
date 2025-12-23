package ie.atu.login_project.Controller;

import ie.atu.login_project.DTO.BookDTO;
import ie.atu.login_project.DTO.PaymentDTO;
import ie.atu.login_project.FeignClient.BookClient;
import ie.atu.login_project.FeignClient.PaymentClient;
import ie.atu.login_project.Model.Login;
import ie.atu.login_project.Model.PersonDetails;
import ie.atu.login_project.Service.LoginService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class LoginController {
    private final LoginService loginService;
    private final BookClient bookClient;
    private final PaymentClient paymentClient;

    public LoginController(LoginService loginService, BookClient bookClient, PaymentClient paymentClient) {
        this.loginService = loginService;
        this.bookClient = bookClient;
        this.paymentClient = paymentClient;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Login registerUser(@Valid @RequestBody Login registerLogin) {
       return loginService.createLogin(registerLogin);
    }

    @GetMapping("/{Loginid}")
    public ResponseEntity<Login> LoginUser(@PathVariable Long Loginid) {
      Optional<Login> maybe = loginService.getLoginById(Loginid);
      if(maybe.isPresent()) {
          return ResponseEntity.ok(maybe.get());
      }
      else{
          return ResponseEntity.notFound().build();
      }
    }

    @GetMapping
    public List<Login> GetUser(){
        return loginService.getAllLogins();
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Login> updateUser(@RequestParam Long Loginid, @Valid @RequestBody Login updateLogin) {
        Optional<Login> maybe = loginService.getLoginById(Loginid);
        if(maybe.isPresent()) {
            return ResponseEntity.ok(maybe.get());
        }
        else {
        return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{loginId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Login> deleteUser(@RequestParam Long Loginid) {
        Optional<Login> maybe = loginService.getLoginById(Loginid);
        if(maybe.isPresent()) {
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/PersonDetails/{loginId}")
    @ResponseStatus(HttpStatus.CREATED)
    public PersonDetails createPersonDetails(@PathVariable Long loginId, @Valid @RequestBody  PersonDetails personDetails) {
        return loginService.createPersonDetails(loginId, personDetails);
    }

    @GetMapping("/person")
    public List<PersonDetails> GetPersonDetails() {
        return loginService.getAllPersonDetails();
    }

    @DeleteMapping("/person/{personId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<PersonDetails> deletePersonDetails(@RequestParam Long loginid) {
        Optional<Login> maybe = loginService.getLoginById(loginid);
        if(maybe.isPresent()) {
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/updateDetails/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PersonDetails> updatePersonDetails(@RequestParam Long loginid, @Valid @RequestBody PersonDetails personDetails) {
        Optional<Login> maybe = loginService.getLoginById(loginid);
        if(maybe.isPresent()) {
            return ResponseEntity.ok(maybe.get().getPersonDetails());
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/GetBook/{id}")
    public BookDTO getBookID(@PathVariable Long id) {
        return bookClient.getBookID(id);
    }

    @GetMapping("/api/payment-history/{id}")
    public ResponseEntity<PaymentDTO> getPaymentID(@PathVariable("id") Long paymentID) {
        return ResponseEntity.ok(paymentClient.getPaymentID(paymentID));
    }

    @GetMapping("/payment/{id}")
    public ResponseEntity<PaymentDTO> getSelectedPayments(@PathVariable Long id) {

        ResponseEntity<PaymentDTO> response = paymentClient.fromPaymentId(id);

        return ResponseEntity.status(HttpStatus.OK).body(response.getBody());
    }
}
