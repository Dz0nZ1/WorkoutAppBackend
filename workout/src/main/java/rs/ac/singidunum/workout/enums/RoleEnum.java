package rs.ac.singidunum.workout.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.List;
import static rs.ac.singidunum.workout.enums.PermissionEnum.*;


@RequiredArgsConstructor
public enum RoleEnum {
//    User(Collections.emptySet()),

    User(
            Set.of(
                   UserRead,
                   UserUpdate,
                   UserCreate,
                   UserDelete
            )
    ),
    Admin(
            Set.of(
                    AdminRead,
                    AdminUpdate,
                    AdminDelete,
                    AdminCreate,
                    EmployeeRead,
                    EmployeeCreate,
                    EmployeeUpdate,
                    EmployeeDelete,
                    UserRead,
                    UserUpdate,
                    UserDelete,
                    UserCreate
            )
    ),
    Employee(
            Set.of(
                    EmployeeRead,
                    EmployeeCreate,
                    EmployeeUpdate,
                    EmployeeDelete
            )
    );

    @Getter
    private final Set<PermissionEnum> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
