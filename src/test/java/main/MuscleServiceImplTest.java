package main;

import main.muscle.Muscle;
import main.muscle.MuscleRepositoryPort;
import main.muscle.MuscleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MuscleServiceImplTest {

    private MuscleServiceImpl muscleService;
    private List<Muscle> muscles = new ArrayList<>();

    @BeforeEach
    void setUp() {
        MuscleRepositoryPort muscleRepo = new InMemoryMuscleRepository();
        muscleService = new MuscleServiceImpl(muscleRepo);

        // Добавляем тестовые данные
        muscles.add(new Muscle(1L, "Biceps", null));
        muscles.add(new Muscle(2L, "Triceps", null));
    }

    @Test
    void testGetAllMuscles() {
        List<Muscle> result = muscleService.getAllMuscles();
        assertEquals(2, result.size());
    }

    @Test
    void testGetMuscleById() {
        Muscle found = muscleService.getMuscleById(1L);
        assertNotNull(found);
        assertEquals("Biceps", found.getName());
    }

    @Test
    void testAddMuscle() {
        Muscle newMuscle = muscleService.addMuscle("Quadriceps");
        assertEquals("Quadriceps", newMuscle.getName());
        assertEquals(3, muscles.size()); // Проверяем, что запись добавилась
    }

    @Test
    void testAddExistingMuscleShouldFail() {
        assertThrows(IllegalArgumentException.class, () -> muscleService.addMuscle("Biceps"));
    }

    @Test
    void testGetMuscleByIdNotFound() {
        assertThrows(RuntimeException.class, () -> muscleService.getMuscleById(99L));
    }

    // Фейковый репозиторий мышц (в памяти)
    private class InMemoryMuscleRepository implements MuscleRepositoryPort {
        @Override
        public List<Muscle> findAllMuscles() {
            return muscles;
        }

        @Override
        public Optional<Muscle> findMuscleById(Long id) {
            return muscles.stream().filter(m -> m.getId().equals(id)).findFirst();
        }

        @Override
        public Muscle save(Muscle muscle) {
            if (existsByName(muscle.getName())) {
                throw new IllegalArgumentException("Muscle with name '" + muscle.getName() + "' already exists");
            }
            muscles.add(muscle);
            return muscle;
        }

        @Override
        public boolean existsByName(String name) {
            return muscles.stream().anyMatch(m -> m.getName().equals(name));
        }
    }
}
