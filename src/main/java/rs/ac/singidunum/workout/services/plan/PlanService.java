package rs.ac.singidunum.workout.services.plan;

import rs.ac.singidunum.workout.models.workouts.Plan;

import java.util.List;

public interface PlanService {

    List<Plan> getAllPlans();

    List<Plan> getAllPlansByName(String name);

    List<Plan> getAllPlansById(Long id);

    Plan getPlan(Long id);

    Plan createPlan(Plan plan);

    Plan updatePlan(Plan plan, Long id);

    void deletePlan(Long id);

}
