package rs.ac.singidunum.workout.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.singidunum.workout.enums.RoleEnum;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequestModel {
    @NotBlank(message = "First name is mandatory")
    private String firstName;
    @NotBlank(message = "Last name is mandatory")
    private String lastName;
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email address must be in valid format")
    private String email;
    @NotBlank(message = "Password is mandatory")
    @Min(value = 8, message = "Password must have at least 8 characters")
    private String password;
    private RoleEnum role;

}