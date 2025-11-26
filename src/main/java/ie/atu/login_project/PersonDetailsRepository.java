package ie.atu.login_project;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonDetailsRepository extends JpaRepository<PersonDetails, Long> {
    Optional<PersonDetails> findByUsername(String username);
}
