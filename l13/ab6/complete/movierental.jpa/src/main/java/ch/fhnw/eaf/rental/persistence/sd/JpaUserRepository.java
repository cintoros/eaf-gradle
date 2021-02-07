package ch.fhnw.eaf.rental.persistence.sd;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import ch.fhnw.eaf.rental.model.User;
import ch.fhnw.eaf.rental.persistence.UserRepository;

@Profile({"sd","prod"})
public interface JpaUserRepository extends UserRepository, JpaRepository<User, Long> {
}
