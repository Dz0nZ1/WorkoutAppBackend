package rs.ac.singidunum.workout.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.workout.models.workouts.PropertyModel;

import java.util.List;

@Repository
public interface PropertiesRepository extends JpaRepository<PropertyModel, Long> {
    List<PropertyModel> findAllByForPlan(String forPlan);
}
