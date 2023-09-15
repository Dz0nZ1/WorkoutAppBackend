package rs.ac.singidunum.workout.models.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.singidunum.workout.enums.RoleEnum;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationResponseModel {
    @JsonProperty("access_token")
    private String accessToken;
    private Long user_id;
    private String refreshToken;
    private String email;
    private String firstName;
    private String lastName;
    private RoleEnum role;
}
