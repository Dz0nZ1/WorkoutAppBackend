package rs.ac.singidunum.workout.services.exercises;

import rs.ac.singidunum.workout.exceptions.ExerciseNotFoundException;
import rs.ac.singidunum.workout.models.workouts.Exercise;

import java.util.List;

public interface ExerciseService {

    List<Exercise> gelAllExercises();

    Exercise createExercise(Exercise exercise);

    Exercise getExercise(Long exerciseId) throws ExerciseNotFoundException;

    Exercise findExerciseByName(String name);

    void deleteExerciseByName(String name);

    void deleteExercise(Long exerciseId);

    Exercise updateExercise(Exercise exercise, Long exerciseId);

}
