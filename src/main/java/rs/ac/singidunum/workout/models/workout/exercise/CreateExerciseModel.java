package rs.ac.singidunum.workout.models.workout.exercise;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateExerciseModel {
    private String name;
    private String photo;
    private String category;
}
