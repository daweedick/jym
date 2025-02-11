package main.user;
import main.user.WorkoutPlan.WorkoutPlan;

import java.util.List;
public interface UserService {
    User createUser(String username, String email, String password) ;

    User getUserById(Long id);

    User getUserByUsername(String username);

    List<User> getAllUsers();

    User updateUser(Long id, String username, String email);

    void deleteUser(Long id);


}
