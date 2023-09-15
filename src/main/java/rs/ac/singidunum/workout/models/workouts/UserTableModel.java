package rs.ac.singidunum.workout.models.workouts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserTableModel {
    private String first_name;
    private String last_name;
    private String email;
}
