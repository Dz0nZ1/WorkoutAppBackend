package rs.ac.singidunum.workout.entities.auth;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import rs.ac.singidunum.workout.enums.RoleEnum;

import java.util.Collection;

@Data
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;
    @Column(name = "first_name")
    @NotBlank(message = "First name is mandatory")
    private String firstName;
    @Column(name = "last_name")
    @NotBlank(message = "Last name is mandatory")
    private String lastName;
    @Column(name = "email", unique = true)
    @NotBlank(message = "Email is mandatory")
    @Email
    private String email;
    @NotBlank(message = "Password is mandatory")
    private String password;
    @Enumerated(EnumType.STRING)
    private RoleEnum role;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
