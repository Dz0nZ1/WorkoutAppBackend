package rs.ac.singidunum.workout.models.workout.property;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdatePropertyModel {
    private Integer sets;
    private Integer reps;
    private Double weight;
}
