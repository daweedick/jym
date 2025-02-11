package main;

import main.user.User;
import main.user.UserRepositoryPort;
import main.user.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

    private UserServiceImpl userService;
    private InMemoryUserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = new InMemoryUserRepository();
        userService = new UserServiceImpl(userRepository);

        // Testdaten hinzufügen
        userService.createUser("Alice", "alice@example.com", "password123");
        userService.createUser("Bob", "bob@example.com", "securePass");
    }

    @Test
    void testCreateUser() {
        User newUser = userService.createUser("Charlie", "charlie@example.com", "pass123");
        assertEquals("Charlie", newUser.getUsername());
        assertEquals(3, userRepository.users.size()); // Benutzer wurde hinzugefügt
    }

    @Test
    void testCreateUserWithExistingUsername() {
        assertThrows(IllegalArgumentException.class,
                () -> userService.createUser("Alice", "newalice@example.com", "newpass"));
    }

    @Test
    void testGetUserById() {
        User user = userService.getUserById(1L);
        assertNotNull(user);
        assertEquals("Alice", user.getUsername());
    }

    @Test
    void testGetUserByIdNotFound() {
        assertThrows(IllegalArgumentException.class, () -> userService.getUserById(99L));
    }

    @Test
    void testGetUserByUsername() {
        User user = userService.getUserByUsername("Bob");
        assertNotNull(user);
        assertEquals("Bob", user.getUsername());
    }

    @Test
    void testUpdateUser() {
        User updatedUser = userService.updateUser(1L, "AliceUpdated", "alice@newmail.com");
        assertEquals("AliceUpdated", updatedUser.getUsername());
        assertEquals("alice@newmail.com", updatedUser.getEmail());
    }

    @Test
    void testDeleteUser() {
        userService.deleteUser(2L);
        assertThrows(IllegalArgumentException.class, () -> userService.getUserById(2L));
    }

    // In-Memory-UserRepository für Tests
    private static class InMemoryUserRepository implements UserRepositoryPort {
        private final List<User> users = new ArrayList<>();
        private long idCounter = 1L;

        @Override
        public Optional<User> findById(Long id) {
            return users.stream().filter(u -> u.getId().equals(id)).findFirst();
        }

        @Override
        public Optional<User> findByUsername(String username) {
            return users.stream().filter(u -> u.getUsername().equals(username)).findFirst();
        }

        @Override
        public List<User> findAllUsers() {
            return new ArrayList<>(users);
        }

        @Override
        public boolean existsByUsername(String username) {
            return users.stream().anyMatch(u -> u.getUsername().equals(username));
        }

        @Override
        public boolean existsById(Long id) {
            return users.stream().anyMatch(u -> u.getId().equals(id));
        }

        @Override
        public User save(User user) {
            if (user.getId() == null) {
                user.setId(idCounter++);
            }
            users.add(user);
            return user;
        }

        @Override
        public void deleteById(Long id) {
            users.removeIf(u -> u.getId().equals(id));
        }
    }
}
