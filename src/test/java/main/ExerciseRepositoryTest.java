package main;
import main.exercise.Exercise;
import main.exercise.ExerciseRepositoryAdapter;
import main.exercise.ExerciseRepositoryPort;
import main.muscle.Muscle;
import main.muscle.MuscleRepositoryPort;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(TestContainerConfig.class)
class ExerciseRepositoryTest {

    @Autowired
    private ExerciseRepositoryPort exerciseRepositoryPort;

    @Autowired
    private MuscleRepositoryPort MuscleRepositoryPort;

    @BeforeAll
    static void beforeAll() {
        TestContainerConfig.startContainer();
    }

    @Test
    void testSaveAndFindExercise() {
        Muscle muscle = new Muscle("Chest");
        MuscleRepositoryPort.save(muscle);

        Exercise exercise = new Exercise("Bench Press", muscle, "Press with barbell", "/images/bench.jpg");
        exerciseRepositoryPort.save(exercise);

        List<Exercise> exercises = exerciseRepositoryPort.findByMuscleName("Chest");

        assertThat(exercises).hasSize(1);
        assertThat(exercises.get(0).getName()).isEqualTo("Bench Press");
    }
}

