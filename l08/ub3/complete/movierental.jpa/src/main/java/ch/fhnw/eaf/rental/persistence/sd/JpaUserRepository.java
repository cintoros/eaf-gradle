package ch.fhnw.eaf.rental.persistence.sd;

import ch.fhnw.eaf.rental.model.User;
import ch.fhnw.eaf.rental.persistence.UserRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

@Profile({"sd", "prod"})
public interface JpaUserRepository extends UserRepository, JpaRepository<User, Long> {
}
