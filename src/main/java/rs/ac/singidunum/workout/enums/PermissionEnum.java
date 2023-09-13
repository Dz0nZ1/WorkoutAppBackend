package rs.ac.singidunum.workout.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PermissionEnum {
    AdminRead("admin:read"),
    AdminUpdate("admin:update"),
    AdminCreate("admin:create"),
    AdminDelete("admin:delete"),
    EmployeeRead("employee:read"),
    EmployeeUpdate("employee:update"),
    EmployeeCreate("employee:create"),
    EmployeeDelete("employee:delete"),

    UserRead("user:read"),
    UserUpdate("user:update"),
    UserCreate("user:create"),
    UserDelete("user:delete"),


    ;



    @Getter
    private final String permission;
}
