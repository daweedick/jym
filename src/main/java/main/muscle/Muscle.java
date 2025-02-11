package main.muscle;

import jakarta.persistence.*;
import main.exercise.Exercise;

import java.util.List;

@Entity
public class Muscle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    @OneToMany(mappedBy = "muscle", fetch = FetchType.LAZY)
    List<Exercise> exercises;

    public Muscle(Long id, String name, List<Exercise> exercises) {
        this.id = id;
        this.name = name;
        this.exercises = exercises;
    }

    public Muscle(String name) {
        this.name = name;
    }

    public Muscle(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }
}
