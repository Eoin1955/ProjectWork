package ie.atu.login_project.FeignClient;


import ie.atu.login_project.DTO.BookDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "book-service",
        url = "${book.service.base-url}"
)

public interface BookClient {
    @GetMapping("/api/books/{id}")
    BookDTO getBookID(@PathVariable Long paymentId);
}
