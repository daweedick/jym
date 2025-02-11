package main.user.WorkoutPlan;


import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class WorkoutPlanMutationResolver {

    private final WorkoutPlanService workoutPlanService;

    public WorkoutPlanMutationResolver(WorkoutPlanService workoutPlanService) {
        this.workoutPlanService = workoutPlanService;
    }

    @MutationMapping
    public WorkoutPlan createWorkoutPlan(@Argument Long userId, @Argument String planName) {
        return workoutPlanService.createWorkoutPlan(userId, planName);
    }

    @MutationMapping
    public boolean addExerciseToWorkoutPlan(@Argument Long planId, @Argument Long exerciseId) {
        workoutPlanService.addExerciseToWorkoutPlan(planId, exerciseId);
        return true;
    }

    @MutationMapping
    public boolean deleteWorkoutPlan(@Argument Long planId) {
        workoutPlanService.deleteWorkoutPlan(planId);
        return true;
    }
}
