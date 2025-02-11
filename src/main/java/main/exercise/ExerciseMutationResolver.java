package main.exercise;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Controller;

@Controller
public class ExerciseMutationResolver {
    private final ExerciseService exerciseService;

    public ExerciseMutationResolver(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @MutationMapping
    public Exercise addExercise(
            @Argument String name,
            @Argument Long muscleId,
            @Argument String description,
            @Argument String descriptionPath
    ) {
        return exerciseService.addExercise(name, muscleId, description, descriptionPath);
    }

    @MutationMapping
    public Exercise updateExercise(@Argument Long id,
                                   @Argument String name,
                                   @Argument Long muscleId,
                                   @Argument String description,
                                   @Argument String descriptionPath){
        return exerciseService.updateExercise(id, name, muscleId, description, descriptionPath);
    }

    @MutationMapping
    public boolean deleteExercise(@Argument Long id){
        exerciseService.deleteExercise(id);
        return true;
    }
}
