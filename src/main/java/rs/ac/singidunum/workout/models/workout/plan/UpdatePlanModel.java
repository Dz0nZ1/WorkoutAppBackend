package rs.ac.singidunum.workout.models.workout.plan;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.singidunum.workout.models.workout.exercise.ExerciseModel;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdatePlanModel {
    private String name;
    private Set<ExerciseModel> exercises;

}
