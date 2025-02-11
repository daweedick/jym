package main.exercise;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ExerciseRepositoryPort {

    Optional<Exercise> findExerciseById(Long id);
    List<Exercise> findByMuscleName(String muscleName);
    Page<Exercise> findByMuscleName(String muscleName, Pageable pageable);

    Exercise save(Exercise exercise);


    boolean existsByName(String name);

    boolean existsById(Long id);
    void deleteById(Long id);

}
