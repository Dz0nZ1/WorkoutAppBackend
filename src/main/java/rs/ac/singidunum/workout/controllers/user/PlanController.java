package rs.ac.singidunum.workout.controllers.user;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import rs.ac.singidunum.workout.exceptions.InvalidArgumentsHandler;
import rs.ac.singidunum.workout.models.workouts.PlanModel;
import rs.ac.singidunum.workout.services.plan.PlanService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@PreAuthorize("hasRole('User')")
@RequestMapping("/api/v1/plan")
public class PlanController {

    private final PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<List<PlanModel>> getAllPlans(){
        return new ResponseEntity<>(planService.getAllPlans(), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<List<PlanModel>> getAllPlansById(@PathVariable("id") Long id){
        return new ResponseEntity<>(planService.getAllPlansById(id), HttpStatus.OK);
    }

    @GetMapping("/get/{name}")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<List<PlanModel>> getAllPlansByName(@PathVariable("name") String name){
        return new ResponseEntity<>(planService.getAllPlansByName(name), HttpStatus.OK);
    }


    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<PlanModel> getExercise(@PathVariable("id") Long planId){
        return new ResponseEntity<>(planService.getPlan(planId), HttpStatus.OK);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('user:create')")
    public ResponseEntity<?> createExercise(@Valid @RequestBody PlanModel planModel, BindingResult result) {

        if(result.hasErrors()) {
            var exceptionHandler = new InvalidArgumentsHandler();
            return new ResponseEntity<>(exceptionHandler.getErrorMessages(result), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(planService.createPlan(planModel), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('user:delete')")
    public ResponseEntity<Map<String, String>> deletePlan(@PathVariable("id") Long planId) {
        planService.deletePlan(planId);
        Map<String, String> response = new HashMap<>();
        response.put("defaultMessage", "Plan was deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('user:update')")
    public ResponseEntity<?> updateExercise(@Valid @RequestBody PlanModel planModel, @PathVariable("id") Long planId , BindingResult result){

        if(result.hasErrors()) {
            var exceptionHandler = new InvalidArgumentsHandler();
            return new ResponseEntity<>(exceptionHandler.getErrorMessages(result), HttpStatus.BAD_REQUEST);
        }

        return  new ResponseEntity<>(planService.updatePlan(planModel, planId), HttpStatus.OK);
    }




}
