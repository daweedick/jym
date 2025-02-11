package main.user.WorkoutPlan;

import main.exercise.Exercise;
import main.exercise.ExerciseRepository;
import main.exercise.ExerciseRepositoryPort;
import main.user.User;
import main.user.UserRepositoryPort;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkoutPlanServiceImpl implements WorkoutPlanService {

    private final WorkoutPlanRepositoryPort workoutPlanRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;
    private final ExerciseRepositoryPort exerciseRepositoryPort;

    public WorkoutPlanServiceImpl(WorkoutPlanRepositoryPort workoutPlanRepositoryPort,
                                  UserRepositoryPort userRepositoryPort,
                                  ExerciseRepositoryPort exerciseRepositoryPort) {
        this.workoutPlanRepositoryPort = workoutPlanRepositoryPort;
        this.userRepositoryPort = userRepositoryPort;
        this.exerciseRepositoryPort = exerciseRepositoryPort;
    }

    @Override
    public WorkoutPlan createWorkoutPlan(Long userId, String name){
        User user = userRepositoryPort.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        WorkoutPlan workoutPlan = new WorkoutPlan(name, user);
        return workoutPlanRepositoryPort.save(workoutPlan);
    }

    @Override
    public void addExerciseToWorkoutPlan (Long planId, Long exerciseId) {
        Exercise exercise = exerciseRepositoryPort.findExerciseById(exerciseId)
                .orElseThrow(() -> new IllegalArgumentException("Exercise not found"));
        WorkoutPlan workoutPlan = workoutPlanRepositoryPort.findById(planId)
                .orElseThrow(() -> new IllegalArgumentException("WorkoutPlan not found"));
        workoutPlan.addExercise(exercise);
        workoutPlanRepositoryPort.save(workoutPlan);
    }

    @Override
    public List<WorkoutPlan> getWorkoutPlansByUserId(Long userId){
        return workoutPlanRepositoryPort.findByUserId(userId);
    }

    @Override
    public WorkoutPlan getWorkoutPlanById(Long id) {
        Optional<WorkoutPlan> optionalWorkoutPlan = workoutPlanRepositoryPort.findById(id);
        if (optionalWorkoutPlan.isPresent()) {
            return optionalWorkoutPlan.get();
        } else {
            throw new RuntimeException("Exercise with ID: " + id + "not found");
        }
    }

    @Override
    public void deleteWorkoutPlan(Long planId) {
        workoutPlanRepositoryPort.deleteById(planId);
    }
}
