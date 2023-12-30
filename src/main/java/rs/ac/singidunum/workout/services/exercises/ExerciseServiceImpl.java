package rs.ac.singidunum.workout.services.exercises;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.singidunum.workout.exceptions.ExerciseNotFoundException;
import rs.ac.singidunum.workout.mappers.ExerciseMapper;
import rs.ac.singidunum.workout.models.workout.exercise.CreateExerciseModel;
import rs.ac.singidunum.workout.models.workout.exercise.ExerciseModel;
import rs.ac.singidunum.workout.models.workout.exercise.UpdateExerciseModel;
import rs.ac.singidunum.workout.repositories.ExerciseRepository;

import java.util.List;
import java.util.Set;

@Service
public class ExerciseServiceImpl implements ExerciseService {


    private final ExerciseRepository exerciseRepository;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public Set<ExerciseModel> gelAllExercises() {
        return ExerciseMapper.mapExerciseSetToExerciseSetModel(exerciseRepository.findAllSet());
    }

    @Override
    public ExerciseModel createExercise(CreateExerciseModel exerciseModel) {
        var exercise = ExerciseMapper.mapCreateExerciseModelToExercise(exerciseModel);
        exerciseRepository.save(exercise);
        return ExerciseMapper.mapExerciseToExerciseModel(exercise);
    }

    @Override
    public ExerciseModel getExercise(Long exerciseId) throws ExerciseNotFoundException {
        var exercise = exerciseRepository.findById(exerciseId).orElseThrow(() -> new ExerciseNotFoundException("This exercise doesn't exist"));
        return ExerciseMapper.mapExerciseToExerciseModel(exercise);
    }

    @Override
    public ExerciseModel findExerciseByName(String name) {
        return ExerciseMapper.mapExerciseToExerciseModel(exerciseRepository.findByName(name));
    }

    @Override
    public void deleteExerciseByName(String name) {
        exerciseRepository.deleteByName(name);
    }

    @Transactional
    @Override
    public void deleteExercise(Long exerciseId) {
        exerciseRepository.deleteByExerciseId(exerciseId);
    }

    @Override
    public ExerciseModel updateExercise(UpdateExerciseModel exercise, Long exerciseId) {
        var newExercise = exerciseRepository.findById(exerciseId).orElseThrow(() -> new ExerciseNotFoundException("Exercise doesn't exist "));
        if(exercise.getName() != null) {
            newExercise.setName(exercise.getName());
        }
        if(exercise.getCategory() != null) {
            newExercise.setCategory(exercise.getCategory());
        }
        if(exercise.getPhoto() != null) {
            newExercise.setPhoto(exercise.getPhoto());
        }
        return ExerciseMapper.mapExerciseToExerciseModel(exerciseRepository.save(newExercise));
    }
}
