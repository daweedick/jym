package main.exercise;
import main.muscle.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseServiceImpl implements ExerciseService {
    private final ExerciseRepositoryPort exerciseRepositoryPort;
    private final MuscleRepositoryPort muscleRepositoryPort;

    public ExerciseServiceImpl(ExerciseRepositoryPort exerciseRepositoryPort, MuscleRepositoryPort muscleRepositoryPort) {
        this.exerciseRepositoryPort = exerciseRepositoryPort;
        this.muscleRepositoryPort = muscleRepositoryPort;
    }

    @Override
    public Exercise getExerciseById(Long id){
        Optional<Exercise> optionalExercise = exerciseRepositoryPort.findExerciseById(id);
        if (optionalExercise.isPresent()){
            return optionalExercise.get();
        } else {
            throw new IllegalArgumentException("Exercise with ID: " + id + "not found");
        }
    }
    @Override
    public Page<Exercise> getExercisesByMuscle(String muscleName, Pageable pageable) {
        if (muscleName == null || muscleName.isBlank()) {
            throw new IllegalArgumentException("Muscle with name " + muscleName + " not found");
        }
        if (pageable.isUnpaged()){
            List<Exercise> exercises = exerciseRepositoryPort.findByMuscleName(muscleName);
            return new PageImpl<>(exercises); // Оборачиваем список в страницу

        }
        return exerciseRepositoryPort.findByMuscleName(muscleName, pageable);
    }

    @Override
    public Exercise addExercise(String name, Long muscleId, String description, String descriptionPath){

        if (exerciseRepositoryPort.existsByName(name)) {
            throw new IllegalArgumentException("Exercise with name '" + name + "' already exists");
        }

        Optional<Muscle> optionalMuscle = muscleRepositoryPort.findMuscleById(muscleId);
        if (!optionalMuscle.isPresent()) {
            throw new IllegalArgumentException("Muscle with id: " + muscleId +"not found");
        }

        Muscle muscle = optionalMuscle.get();

        Exercise exercise = new Exercise(null, name, muscle, description, descriptionPath);
        return exerciseRepositoryPort.save(exercise);
    }

    @Override
    public Exercise updateExercise(Long id, String name, Long muscleId, String description, String descriptionPath){

        Exercise existingExercise = exerciseRepositoryPort.findExerciseById(id)
                .orElseThrow(() -> new IllegalArgumentException("Exercise not found for id: " + id));

        if (name != null && !name.isBlank()) {
            existingExercise.setName(name);
        }

        if (muscleId != null) {
            Muscle muscle = muscleRepositoryPort.findMuscleById(muscleId)
                    .orElseThrow(() -> new IllegalArgumentException("Muscle not found for id: " + muscleId));
            existingExercise.setMuscle(muscle);
        }

        if (description != null && !description.isBlank()) {
            existingExercise.setDescription(description);
        }

        if (descriptionPath != null && !descriptionPath.isBlank()) {
            existingExercise.setDescriptionPath(descriptionPath);
        }
        return exerciseRepositoryPort.save(existingExercise);
    }

    @Override
    public void deleteExercise (Long id){
        if (!exerciseRepositoryPort.existsById(id)){
            throw new IllegalArgumentException("no found");
        }
        exerciseRepositoryPort.deleteById(id);
    }
}
