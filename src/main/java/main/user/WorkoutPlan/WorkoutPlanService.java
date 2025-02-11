package main.user.WorkoutPlan;

import main.exercise.Exercise;

import java.util.List;
import java.util.Optional;

public interface WorkoutPlanService {

    WorkoutPlan createWorkoutPlan(Long userId, String name);

    void addExerciseToWorkoutPlan (Long planId, Long exerciseId);

    List<WorkoutPlan> getWorkoutPlansByUserId(Long userId);

    WorkoutPlan getWorkoutPlanById(Long planId);

    void deleteWorkoutPlan(Long planId);
}
