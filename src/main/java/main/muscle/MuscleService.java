package main.muscle;

import java.util.List;

public interface MuscleService {
    List<Muscle> getAllMuscles();
    Muscle getMuscleById(Long id);

    Muscle addMuscle(String name);
}
