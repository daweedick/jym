package main.user.WorkoutPlan;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class WorkoutPlanQueryResolver {

    private final WorkoutPlanService workoutPlanService;

    public WorkoutPlanQueryResolver(WorkoutPlanService workoutPlanService) {
        this.workoutPlanService = workoutPlanService;
    }

    @QueryMapping
    public List<WorkoutPlan> getWorkoutPlansByUserId(@Argument Long userId) {
        return workoutPlanService.getWorkoutPlansByUserId(userId);
    }

    @QueryMapping
    public WorkoutPlan getWorkoutPlanById(@Argument Long planId) {
        return workoutPlanService.getWorkoutPlanById(planId);
    }
}
