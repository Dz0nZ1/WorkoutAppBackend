package rs.ac.singidunum.workout.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.workout.entities.workouts.Plan;
import rs.ac.singidunum.workout.entities.workouts.Property;

import java.util.List;

@Repository
public interface PropertiesRepository extends JpaRepository<Property, Long> {
    List<Property> findAllByForExercise(String forExercise);

    List<Property> findAllByPlan(Plan plan);
}
