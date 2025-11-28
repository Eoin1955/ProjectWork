package ie.atu.login_project.Repository;

import ie.atu.login_project.Model.PersonDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonDetailsRepository extends JpaRepository<PersonDetails, Long> {
    Optional<PersonDetails> findByFirstName(String firstName);
}
