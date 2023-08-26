package rs.ac.singidunum.workout.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    @GetMapping
    public String get() {
        return "get employee method";
    }

    @PostMapping
    public String post() {
        return "post employee method";
    }

    @PutMapping
    public String put() {
        return "put employee method";
    }

    @DeleteMapping
    public String delete() {
        return "delete employee method";
    }
}
