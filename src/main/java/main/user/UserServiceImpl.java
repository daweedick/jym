package main.user;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepositoryPort userRepositoryPort;

    public UserServiceImpl(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public  User createUser(String username, String email, String password){
        if (userRepositoryPort.existsByUsername(username)){
            throw new IllegalArgumentException("Username already exists");
        }
        if (email == null || email.isBlank()){
            throw new IllegalArgumentException("Wrong email");
        }
        if (password == null || password.isBlank()){
            throw new IllegalArgumentException("Password cannot be empty");
        }
        User user = new User(null, username, email, password);
        return userRepositoryPort.save(user);
    }

    @Override
    public User getUserById(Long id){
        return userRepositoryPort.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User doesn´t exist"));
    }

    @Override
    public User getUserByUsername(String username){
        return userRepositoryPort.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User doesn´t exist"));
    }

    @Override
    public List<User> getAllUsers(){
        return userRepositoryPort.findAllUsers();
    }

    @Override
    public User updateUser(Long id, String username, String email){
        User existingUser = userRepositoryPort.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found for id: " + id));
        if (username != null && !username.isBlank()) {
            existingUser.setUsername(username);
        }

        if (email != null && !email.isBlank()) {
            existingUser.setEmail(email);
        }
        return userRepositoryPort.save(existingUser);
    }

    @Override
    public void deleteUser(Long id){
        if (!userRepositoryPort.existsById(id)){
            throw new IllegalArgumentException("User with id " + id + " not found");
        }
        userRepositoryPort.deleteById(id);
    }
}

