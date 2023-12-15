package rs.ac.singidunum.workout.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.workout.entities.workouts.Plan;

import java.util.List;


@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
    List<Plan> findAllByIdentity(Long id);

    List<Plan> findAllByName(String name);

}
