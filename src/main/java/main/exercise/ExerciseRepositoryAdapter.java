package main.exercise;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
    public class ExerciseRepositoryAdapter implements ExerciseRepositoryPort {
    private final ExerciseRepository exerciseRepository;

    public ExerciseRepositoryAdapter(ExerciseRepository exerciseRepository) {
            this.exerciseRepository = exerciseRepository;
    }

    @Override
    public Optional<Exercise> findExerciseById(Long id){
        return exerciseRepository.findById(id);
    }

    @Override
    public Page<Exercise> findByMuscleName(String muscleName, Pageable pageable) {
        return exerciseRepository.findByMuscleName(muscleName, pageable);
    }

    @Override
    public List<Exercise> findByMuscleName(String muscleName) {
        return exerciseRepository.findByMuscleName(muscleName, Pageable.unpaged()).getContent();
    }

    @Override
    public Exercise save(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    @Override
    public boolean existsByName(String name) {
        return exerciseRepository.existsByName(name);
    }

    @Override
    public boolean existsById(Long id ) {return  exerciseRepository.existsById(id); }

    @Override
    public void deleteById(Long id)  { exerciseRepository.deleteById(id); }
}

