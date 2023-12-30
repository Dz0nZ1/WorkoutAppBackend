package rs.ac.singidunum.workout.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.workout.entities.workouts.Exercise;

import java.util.Set;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    @Query(value ="SELECT * FROM exercises" ,nativeQuery = true)
    Set<Exercise> findAllSet();
    Exercise findByName(String name);

    void deleteByName(String name);

    void deleteByExerciseId(Long exercise_id);


}
