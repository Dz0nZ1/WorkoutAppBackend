package rs.ac.singidunum.workout.models.workout.plan;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.singidunum.workout.entities.auth.User;
import rs.ac.singidunum.workout.entities.workouts.Exercise;
import rs.ac.singidunum.workout.entities.workouts.Property;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatePlanModel {
    private String name;
    private Long identity;
    private User user;
    private Set<Exercise> exercises ;
    private List<Property> properties;
}
