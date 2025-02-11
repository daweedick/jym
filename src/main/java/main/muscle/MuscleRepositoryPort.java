package main.muscle;

import java.util.List;
import java.util.Optional;


public interface MuscleRepositoryPort {
    List<Muscle> findAllMuscles();

    Optional<Muscle> findMuscleById(Long id);

    Muscle save(Muscle muscle);

    boolean existsByName(String name);
}
