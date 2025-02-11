package main.user;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryPort {

    Optional<User> findById(Long id);

    List<User> findAllUsers();

    User save(User user);

    void deleteById(Long id);

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsById(Long id);


}
