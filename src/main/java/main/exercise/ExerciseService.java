package main.exercise;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ExerciseService {

    Exercise getExerciseById(Long id);
    Page<Exercise> getExercisesByMuscle(String muscleName, Pageable pageable);

    Exercise addExercise(String name, Long muscleId, String description, String descriptionPath);

    Exercise updateExercise(Long id, String name, Long muscleId, String description, String descriptionPath);
    void deleteExercise(Long id);
}

