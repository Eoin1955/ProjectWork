package ie.atu.login_project.FeignClient;

import ie.atu.login_project.DTO.PaymentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "payment-service",
        url = "${payment.service.base-url}"
)
public interface PaymentClient {

    @GetMapping("/api/payment/{id}")
    PaymentDTO getPaymentID(@PathVariable("id") Long paymentID);

    @GetMapping("/api/payment/{paymentId}")
    ResponseEntity<PaymentDTO> fromPaymentId(@PathVariable Long paymentId);
}



