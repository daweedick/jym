package main.exercise;


import jakarta.persistence.*;
import main.muscle.Muscle;


@Entity
public class Exercise {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "muscle_id", nullable = false) // Связь через столбец muscle_id
    private Muscle muscle;
    private String description;
    private String descriptionPath;


    public Exercise(Long id, String name, Muscle muscle, String description, String description_path) {
        this.id = id;
        this.name = name;
        this.muscle = muscle;
        this.description = description;
        this.descriptionPath = description_path;
    }

    public Exercise(String name, Muscle muscle, String description, String description_path) {
        this.name = name;
        this.muscle = muscle;
        this.description = description;
        this.descriptionPath = description_path;
    }

    public Exercise() {

    }

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

    public Muscle getMuscle() {
        return muscle;
    }

    public void setMuscle(Muscle muscle) {
        this.muscle = muscle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionPath() {
        return descriptionPath;
    }

    public void setDescriptionPath(String descriptionPath) {
        this.descriptionPath = descriptionPath;
    }
}
