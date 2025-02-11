package main.user;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final UserRepository userRepository;

    public UserRepositoryAdapter (UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findById(Long id){ return userRepository.findById(id);}

    @Override
    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    @Override
    public User save (User user){
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id){
        userRepository.deleteById(id);
    }

    @Override
    public boolean existsByUsername(String username){
       return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsById(Long id){
        return userRepository.existsById(id);
    }

    @Override
    public Optional<User>  findByUsername(String username){ return userRepository.findByUsername(username);}
}
