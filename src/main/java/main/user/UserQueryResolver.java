package main.user;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Controller;

import java.util.List;
@Controller
public class UserQueryResolver {
    private final UserService userService;

    public UserQueryResolver(UserService userService) {
        this.userService = userService;
    }

    @QueryMapping
    public User user(@Argument Long id){
        return userService.getUserById(id);
    }

    @QueryMapping
    public List<User> users(){
        return userService.getAllUsers();
    }

}
