package rs.ac.singidunum.workout.services.exercises;

import org.springframework.stereotype.Service;
import rs.ac.singidunum.workout.exceptions.ExerciseNotFoundException;
import rs.ac.singidunum.workout.exceptions.UserNotFoundException;
import rs.ac.singidunum.workout.models.workouts.ExerciseModel;
import rs.ac.singidunum.workout.repositories.ExerciseRepository;

import java.util.List;

@Service
public class ExerciseServiceImpl implements ExerciseService {


    private final ExerciseRepository exerciseRepository;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public List<ExerciseModel> gelAllExercises() {
        return exerciseRepository.findAll();
    }

    @Override
    public ExerciseModel createExercise(ExerciseModel exerciseModel) {
        return exerciseRepository.save(exerciseModel);
    }

    @Override
    public ExerciseModel getExercise(Long exerciseId) throws ExerciseNotFoundException {
        return exerciseRepository.findById(exerciseId).orElseThrow(() -> new ExerciseNotFoundException("This exercise doesn't exist"));
    }

    @Override
    public ExerciseModel findByName(String name) {
        return exerciseRepository.findByName(name);
    }

    @Override
    public void deleteExercise(Long exerciseId) {
        exerciseRepository.deleteById(exerciseId);
    }

    @Override
    public ExerciseModel updateExercise(ExerciseModel exerciseModel, Long exerciseId) {
        var newExercise = exerciseRepository.findById(exerciseId).orElseThrow(() -> new ExerciseNotFoundException("Exercise doesn't exist "));
        newExercise.setName(exerciseModel.getName());
        newExercise.setCategory(exerciseModel.getCategory());
        newExercise.setPhoto(exerciseModel.getPhoto());
        newExercise.setDescription(newExercise.getDescription());
        newExercise.setMetric(exerciseModel.getMetric());
        newExercise.setReps(exerciseModel.getReps());
        newExercise.setSets(exerciseModel.getSets());
        newExercise.setWeight(exerciseModel.getWeight());
        return exerciseRepository.save(newExercise);
    }
}
