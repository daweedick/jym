package main;
import main.exercise.*;
import main.muscle.*;
import main.user.User;
import main.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    private final MuscleRepository muscleRepository;
    private final ExerciseRepository exerciseRepository;
    private final UserRepository userRepository;

    public DataInitializer(MuscleRepository muscleRepository,
                           ExerciseRepository exerciseRepository,
                           UserRepository userRepository
    ) {
        this.muscleRepository = muscleRepository;
        this.exerciseRepository = exerciseRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
    }
    }
/*
    @Override
    public void run(String... args) {
        Muscle biceps = new Muscle("Biceps");
        Muscle triceps = new Muscle("Triceps");

        muscleRepository.save(biceps);
        muscleRepository.save(triceps);

        exerciseRepository.save(new Exercise("Push Up", biceps, "A basic push-up exercise", "/path/pushup"));
        exerciseRepository.save(new Exercise("Pull Up", biceps, "Pull-up for back and biceps", "/path/pullup"));
        exerciseRepository.save(new Exercise("Dips", triceps, "Triceps dips", "/path/dips"));

        userRepository.save(new User( "Dan", "aa", "123"));
    }

 */

