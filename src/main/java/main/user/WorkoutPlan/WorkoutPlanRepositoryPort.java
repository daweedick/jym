package main.user.WorkoutPlan;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface WorkoutPlanRepositoryPort {

    WorkoutPlan save(WorkoutPlan workoutPlan);

    Optional<WorkoutPlan> findById(Long id);

    List<WorkoutPlan> findByUserId(Long userId);

    void deleteById(Long id);
}
