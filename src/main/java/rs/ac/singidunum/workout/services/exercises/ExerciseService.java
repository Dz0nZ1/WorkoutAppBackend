package rs.ac.singidunum.workout.services.exercises;

import rs.ac.singidunum.workout.exceptions.ExerciseNotFoundException;
import rs.ac.singidunum.workout.models.workouts.ExerciseModel;

import java.util.List;

public interface ExerciseService {

    List<ExerciseModel> gelAllExercises();

    ExerciseModel createExercise(ExerciseModel exerciseModel);

    ExerciseModel getExercise(Long exerciseId) throws ExerciseNotFoundException;

    ExerciseModel findExerciseByName(String name);

    void deleteExerciseByName(String name);

    void deleteExercise(Long exerciseId);

    ExerciseModel updateExercise(ExerciseModel exerciseModel, Long exerciseId);

}
