package main.muscle;


import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MuscleServiceImpl implements MuscleService {
    private final MuscleRepositoryPort muscleRepositoryPort;

    public MuscleServiceImpl (MuscleRepositoryPort muscleRepositoryPort){
        this.muscleRepositoryPort = muscleRepositoryPort;
    }
    @Override
    public List<Muscle> getAllMuscles (){
        return muscleRepositoryPort.findAllMuscles();
    }

    @Override
    public Muscle getMuscleById(Long id){
        Optional<Muscle> optionalMuscle = muscleRepositoryPort.findMuscleById(id);
        if (optionalMuscle.isPresent()){
            return optionalMuscle.get();
        } else {
            throw new RuntimeException("Muscle with ID " + id + "not found");
        }
    }
    @Override
    public Muscle addMuscle(String name) {
        if (muscleRepositoryPort.existsByName(name)) {
            throw new IllegalArgumentException("Muscle with name '" + name + "' already exists");
        }

        Muscle muscle = new Muscle();
        muscle.setName(name);
        return muscleRepositoryPort.save(muscle);
    }
}
