package rs.ac.singidunum.workout.services.exercises;

import rs.ac.singidunum.workout.exceptions.ExerciseNotFoundException;
import rs.ac.singidunum.workout.models.workout.exercise.CreateExerciseModel;
import rs.ac.singidunum.workout.models.workout.exercise.ExerciseModel;
import rs.ac.singidunum.workout.models.workout.exercise.UpdateExerciseModel;

import java.util.Set;

public interface ExerciseService {

    Set<ExerciseModel> gelAllExercises();

    ExerciseModel createExercise(CreateExerciseModel exercise);

    ExerciseModel getExercise(Long exerciseId) throws ExerciseNotFoundException;

    ExerciseModel findExerciseByName(String name);

    void deleteExerciseByName(String name);

    void deleteExercise(Long exerciseId);

    ExerciseModel updateExercise(UpdateExerciseModel exercise, Long exerciseId);

}
