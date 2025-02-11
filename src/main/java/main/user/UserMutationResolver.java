package main.user;

import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Controller;

@Controller
public class UserMutationResolver {
    private final UserService userService;

    public UserMutationResolver(UserService userService) {
        this.userService = userService;
    }
    @MutationMapping
    public User createUser(@Argument String username,
                           @Argument String email,
                           @Argument String password) {
        return userService.createUser(username, email, password);
    }

    @MutationMapping
    public User updateUser(@Argument Long id,
                           @Argument String username,
                           @Argument String email) {
        return userService.updateUser(id, username, email);
    }

    @MutationMapping
    public boolean deleteUser(@Argument Long id){
        userService.deleteUser(id);
        return true;
    }

}
