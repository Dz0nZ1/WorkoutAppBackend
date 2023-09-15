package rs.ac.singidunum.workout.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.workout.models.workouts.ExerciseModel;

@Repository
public interface ExerciseRepository extends JpaRepository<ExerciseModel, Long> {

    ExerciseModel findByName(String name);

    void deleteByName(String name);


}
