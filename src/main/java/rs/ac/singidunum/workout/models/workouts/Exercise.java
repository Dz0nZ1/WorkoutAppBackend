package rs.ac.singidunum.workout.models.workouts;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "exercises")
@Builder
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exercise_id")
    private Long exerciseId;
    @Column(name = "name")
    private String name;
    @Column(name = "photo")
    private String photo;
    @Column(name="category")
    private String category;
}
