package rs.ac.singidunum.workout.services.exercises;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.singidunum.workout.exceptions.ExerciseNotFoundException;
import rs.ac.singidunum.workout.entities.workouts.Exercise;
import rs.ac.singidunum.workout.repositories.ExerciseRepository;

import java.util.List;

@Service
public class ExerciseServiceImpl implements ExerciseService {


    private final ExerciseRepository exerciseRepository;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public List<Exercise> gelAllExercises() {
        return exerciseRepository.findAll();
    }

    @Override
    public Exercise createExercise(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    @Override
    public Exercise getExercise(Long exerciseId) throws ExerciseNotFoundException {
        return exerciseRepository.findById(exerciseId).orElseThrow(() -> new ExerciseNotFoundException("This exercise doesn't exist"));
    }

    @Override
    public Exercise findExerciseByName(String name) {
        return exerciseRepository.findByName(name);
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
    public Exercise updateExercise(Exercise exercise, Long exerciseId) {
        var newExercise = exerciseRepository.findById(exerciseId).orElseThrow(() -> new ExerciseNotFoundException("Exercise doesn't exist "));
        newExercise.setName(exercise.getName());
        newExercise.setCategory(exercise.getCategory());
        newExercise.setPhoto(exercise.getPhoto());
        return exerciseRepository.save(newExercise);
    }
}
