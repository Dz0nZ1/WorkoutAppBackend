package rs.ac.singidunum.workout.mappers;

import rs.ac.singidunum.workout.entities.workouts.Exercise;
import rs.ac.singidunum.workout.models.workout.exercise.CreateExerciseModel;
import rs.ac.singidunum.workout.models.workout.exercise.ExerciseModel;
import rs.ac.singidunum.workout.models.workout.exercise.UpdateExerciseModel;

import java.util.ArrayList;
import java.util.List;

public class ExerciseMapper {

    public static ExerciseModel mapExerciseToExerciseModel(Exercise exercise) {
        return ExerciseModel
                .builder()
                .exerciseId(exercise.getExerciseId())
                .photo(exercise.getPhoto())
                .category(exercise.getCategory())
                .name(exercise.getName())
                .build();
    }

    public static Exercise mapExerciseModelToExercise(ExerciseModel exerciseModel){
        return Exercise
                .builder()
                .exerciseId(exerciseModel.getExerciseId())
                .category(exerciseModel.getCategory())
                .name(exerciseModel.getName())
                .photo(exerciseModel.getPhoto())
                .build();
    }

    public static Exercise mapCreateExerciseModelToExercise(CreateExerciseModel exerciseModel) {
        return Exercise
                .builder()
                .name(exerciseModel.getName())
                .category(exerciseModel.getCategory())
                .photo(exerciseModel.getPhoto())
                .build();
    }

    public static Exercise mapUpdateExerciseModelToExercise(UpdateExerciseModel exerciseModel) {
        return Exercise
                .builder()
                .name(exerciseModel.getName())
                .category(exerciseModel.getCategory())
                .photo(exerciseModel.getPhoto())
                .build();
    }


    public static List<ExerciseModel> mapExerciseListToExerciseListModel(List<Exercise> exercises) {
        List<ExerciseModel> modelList = new ArrayList<>();
        for(Exercise exercise : exercises) {
            modelList.add(mapExerciseToExerciseModel(exercise));
        }
        return modelList;
    }

}
