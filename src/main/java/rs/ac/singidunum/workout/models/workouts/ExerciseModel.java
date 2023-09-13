package rs.ac.singidunum.workout.models.workouts;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.singidunum.workout.enums.MetricEnum;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "exercises")
@Builder
public class ExerciseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exercise_id")
    private Long exercise_id;
    @Column(name = "name")
    private String name;
    @Column(name = "photo")
    private String photo;
    @Column(name="category")
    private String category;
    @Column(name = "description")
    private String description;
    @Column(name = "sets")
    private int sets;
    @Column(name = "reps")
    private int reps;
    @Column(name = "weight")
    private double weight;
    @Column(name = "metric")
    @Enumerated(EnumType.STRING)
    private MetricEnum metric;
}
