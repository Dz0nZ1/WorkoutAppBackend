package rs.ac.singidunum.workout.models.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.singidunum.workout.enums.RoleEnum;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserModel {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
