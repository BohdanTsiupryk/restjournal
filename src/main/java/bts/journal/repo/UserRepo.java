package bts.journal.repo;

import bts.journal.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends CrudRepository<User, Long> {
    List<User> findAll();

    Optional<User> findByEmail(String email);

}
