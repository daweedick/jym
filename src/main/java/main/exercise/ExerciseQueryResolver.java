package main.exercise;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Controller;
import org.springframework.data.domain.Page;




import java.util.List;

@Controller
public class ExerciseQueryResolver {

    private final ExerciseService exerciseService;

    public ExerciseQueryResolver(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @QueryMapping
    public Page<Exercise> exercises(@Argument String muscleName,
                                    @Argument  Integer page,
                                    @Argument  Integer size) {
        if (page == null || size == null) {
            return exerciseService.getExercisesByMuscle(muscleName, Pageable.unpaged());
        }
        return exerciseService.getExercisesByMuscle(muscleName, PageRequest.of(page, size));
    }

    @QueryMapping
    public Exercise exercise(@Argument Long id){ return exerciseService.getExerciseById(id);}
}
