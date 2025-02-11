package main.Token;

import main.user.User;
import main.user.UserService;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

@Controller
public class AuthController {
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserService userService, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @MutationMapping
    public String login(@Argument String username, @Argument String password) {
        User user = userService.getUserByUsername(username);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Invalid username or password");
        }
        return jwtUtil.generateToken(username);
    }

    @MutationMapping
    public User register(@Argument String username, @Argument String email, @Argument String password) {
        // Проверяем, существует ли пользователь
        if (userService.getUserByUsername(username) != null) {
            throw new IllegalArgumentException("Username already exists");
        }

        // Шифруем пароль
        String encryptedPassword = passwordEncoder.encode(password);

        // Сохраняем пользователя
        return userService.createUser(username, email, encryptedPassword);
    }
}
