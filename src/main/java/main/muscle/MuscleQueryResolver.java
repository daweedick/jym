package main.muscle;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.Argument;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Controller;


@Controller
    public class MuscleQueryResolver {

    private final MuscleService muscleService;

    public MuscleQueryResolver(MuscleService  muscleService) {
        this.muscleService = muscleService;
    }

    @QueryMapping
    public List<Muscle> muscles (){
        return muscleService.getAllMuscles();
    }

    @QueryMapping
    public Muscle muscle(@Argument Long id) {
        return muscleService.getMuscleById(id);
    }
}
