package rs.ac.singidunum.workout.models.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationRequestModel {

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email address must be in valid format")
    private String email;
    @NotBlank(message = "Password is mandatory")
    private String password;
}
