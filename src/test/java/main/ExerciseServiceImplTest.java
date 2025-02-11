package main;

import main.exercise.Exercise;
import main.exercise.ExerciseRepositoryPort;
import main.exercise.ExerciseServiceImpl;
import main.muscle.Muscle;
import main.muscle.MuscleRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ExerciseServiceImplTest {

    private ExerciseServiceImpl exerciseService;
    private List<Exercise> exercises = new ArrayList<>();
    private List<Muscle> muscles = new ArrayList<>();

    @BeforeEach
    void setUp() {
        // Репозиторий мышц
        MuscleRepositoryPort muscleRepo = new MuscleRepositoryPort() {
            @Override
            public Optional<Muscle> findMuscleById(Long id) {
                return muscles.stream().filter(m -> m.getId().equals(id)).findFirst();
            }

            @Override
            public List<Muscle> findAllMuscles() {
                return muscles;
            }

            @Override
            public Muscle save(Muscle muscle) {
                muscles.add(muscle);
                return muscle;
            }

            @Override
            public boolean existsByName(String name) {
                return muscles.stream().anyMatch(m -> m.getName().equals(name));
            }
        };


        // Репозиторий упражнений (храним в памяти)
        ExerciseRepositoryPort exerciseRepo = new InMemoryExerciseRepository();

        // Инициализируем сервис
        exerciseService = new ExerciseServiceImpl(exerciseRepo, muscleRepo);

        // Добавляем тестовые данные
        Muscle muscle = new Muscle(1L, "Biceps", null);
        muscles.add(muscle);

        Exercise exercise = new Exercise(1L, "Bicep Curl", muscle, "Curling motion", "/images/curl.jpg");
        exercises.add(exercise);
    }

    @Test
    void testGetExerciseById() {
        Exercise found = exerciseService.getExerciseById(1L);
        assertNotNull(found);
        assertEquals("Bicep Curl", found.getName());
    }

    @Test
    void testGetExercisesByMuscle() {
        Page<Exercise> result = exerciseService.getExercisesByMuscle("Biceps", Pageable.unpaged());
        assertEquals(1, result.getTotalElements());
    }

    @Test
    void testAddExercise() {
        Exercise newExercise = exerciseService.addExercise("Tricep Dip", 1L, "Dipping motion", "/images/dip.jpg");
        assertEquals("Tricep Dip", newExercise.getName());
    }

    @Test
    void testUpdateExercise() {
        Exercise updated = exerciseService.updateExercise(1L, "Updated Curl", 1L, "New desc", "/images/new.jpg");
        assertEquals("Updated Curl", updated.getName());
    }

    @Test
    void testDeleteExercise() {
        try {
            exerciseService.deleteExercise(1L);
            assertThrows(IllegalArgumentException.class, () -> exerciseService.getExerciseById(1L));
        } catch (Exception e) {
            e.printStackTrace();
            fail("Unexpected exception: " + e.getClass().getName());
        }
    }


    // Фейковый репозиторий упражнений (в памяти)
    private class InMemoryExerciseRepository implements ExerciseRepositoryPort {
        @Override
        public Optional<Exercise> findExerciseById(Long id) {
            return exercises.stream().filter(e -> e.getId().equals(id)).findFirst();
        }

        @Override
        public List<Exercise> findByMuscleName(String muscleName) {
            return exercises.stream().filter(e -> e.getMuscle().getName().equals(muscleName)).toList();
        }

        @Override
        public Page<Exercise> findByMuscleName(String muscleName, Pageable pageable) {
            return new PageImpl<>(findByMuscleName(muscleName));
        }

        @Override
        public Exercise save(Exercise exercise) {
            exercises.add(exercise);
            return exercise;
        }

        @Override
        public boolean existsByName(String name) {
            return exercises.stream().anyMatch(e -> e.getName().equals(name));
        }

        @Override
        public boolean existsById(Long id) {
            return exercises.stream().anyMatch(e -> e.getId().equals(id));
        }

        @Override
        public void deleteById(Long id) {
            if (!existsById(id)) {
                throw new IllegalArgumentException("Exercise not found");
            }
            exercises.removeIf(e -> e.getId().equals(id));
        }
    }
}
