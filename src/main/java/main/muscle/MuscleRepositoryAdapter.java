package main.muscle;
import main.exercise.Exercise;
import main.exercise.ExerciseRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class MuscleRepositoryAdapter implements MuscleRepositoryPort{
    private final MuscleRepository muscleRepository;

    public MuscleRepositoryAdapter(MuscleRepository muscleRepository) {
        this.muscleRepository = muscleRepository;
    }
    @Override
    public List<Muscle> findAllMuscles() {
        return muscleRepository.findAll();
    }

    @Override
    public Optional<Muscle> findMuscleById(Long id) {

        return muscleRepository.findById(id);
    }
    @Override
    public Muscle save(Muscle muscle) {
        return muscleRepository.save(muscle);
    }
    @Override
    public boolean existsByName(String name) {
        return muscleRepository.existsByName(name);
    }



}

