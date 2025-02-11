package main.muscle;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Controller;

@Controller
public class MuscleMutationResolver {
    private final MuscleService muscleService;

    public MuscleMutationResolver (MuscleService muscleService){
        this.muscleService = muscleService;
    }

    @MutationMapping
    public Muscle addMuscle(@Argument String name){
        return muscleService.addMuscle(name);
    }

}
