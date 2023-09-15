package rs.ac.singidunum.workout.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.workout.models.workouts.PlanModel;

import java.util.List;


@Repository
public interface PlanRepository extends JpaRepository<PlanModel, Long> {
    List<PlanModel> findAllByIdentity(Long id);

}
