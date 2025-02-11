package main.user.WorkoutPlan;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class WorkoutPlanRepositoryAdapter implements WorkoutPlanRepositoryPort {

    private final WorkoutPlanRepository workoutPlanRepository;

    public WorkoutPlanRepositoryAdapter(WorkoutPlanRepository workoutPlanRepository) {
        this.workoutPlanRepository = workoutPlanRepository;
    }

    @Override
    public WorkoutPlan save(WorkoutPlan workoutPlan) {
        return workoutPlanRepository.save(workoutPlan);
    }

    @Override
    public Optional<WorkoutPlan> findById(Long id) {
        return workoutPlanRepository.findById(id);
    }

    @Override
    public List<WorkoutPlan> findByUserId(Long userId) {
        return workoutPlanRepository.findByUserId(userId);
    }

    @Override
    public void deleteById(Long id) {
        workoutPlanRepository.deleteById(id);
    }
}
