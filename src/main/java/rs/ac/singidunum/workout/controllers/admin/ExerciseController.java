package rs.ac.singidunum.workout.controllers.admin;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import rs.ac.singidunum.workout.exceptions.InvalidArgumentsHandler;
import rs.ac.singidunum.workout.models.workout.exercise.CreateExerciseModel;
import rs.ac.singidunum.workout.models.workout.exercise.ExerciseModel;
import rs.ac.singidunum.workout.models.workout.exercise.UpdateExerciseModel;
import rs.ac.singidunum.workout.services.exercises.ExerciseService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/exercise")
@PreAuthorize("hasRole('Admin')")
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseService exerciseService;


    @GetMapping("/all")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<List<ExerciseModel>> getAllExercises(){
        return new ResponseEntity<>(exerciseService.gelAllExercises(), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<ExerciseModel> getExercise(@PathVariable("id") Long exerciseId){
        return new ResponseEntity<>(exerciseService.getExercise(exerciseId), HttpStatus.OK);
    }


    @PostMapping("/create")
    @PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<?> createExercise(@Valid @RequestBody CreateExerciseModel exercise, BindingResult result) {

        if(result.hasErrors()) {
            var exceptionHandler = new InvalidArgumentsHandler();
            return new ResponseEntity<>(exceptionHandler.getErrorMessages(result), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(exerciseService.createExercise(exercise), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('admin:delete')")
    public ResponseEntity<Map<String, String>> delete(@PathVariable("id") Long exerciseId) {
    exerciseService.deleteExercise(exerciseId);
        Map<String, String> response = new HashMap<>();
        response.put("defaultMessage", "Exercise was deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    @DeleteMapping("/deleteone/{name}")
//    @PreAuthorize("hasAuthority('admin:delete')")
//    public ResponseEntity<Map<String, String>> deleteExerciseByName(@PathVariable("name") String name) {
//        exerciseService.deleteExerciseByName(name);
//        Map<String, String> response = new HashMap<>();
//        response.put("defaultMessage", "Exercise was deleted");
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }


    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<?> updateExercise(@Valid @RequestBody UpdateExerciseModel exercise, @PathVariable("id") Long exerciseId , BindingResult result){

        if(result.hasErrors()) {
            var exceptionHandler = new InvalidArgumentsHandler();
            return new ResponseEntity<>(exceptionHandler.getErrorMessages(result), HttpStatus.BAD_REQUEST);
        }

        return  new ResponseEntity<>(exerciseService.updateExercise(exercise, exerciseId), HttpStatus.OK);
    }

}
