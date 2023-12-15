package rs.ac.singidunum.workout.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.workout.models.workouts.Exercise;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    Exercise findByName(String name);

    void deleteByName(String name);

    void deleteByExerciseId(Long exercise_id);


}
