package rs.ac.singidunum.workout.services.plan;

import rs.ac.singidunum.workout.models.workout.plan.CreatePlanModel;
import rs.ac.singidunum.workout.models.workout.plan.PlanModel;
import rs.ac.singidunum.workout.models.workout.plan.UpdatePlanModel;

import java.util.List;

public interface PlanService {

    List<PlanModel> getAllPlans();

    List<PlanModel> getAllPlansByName(String name);

    List<PlanModel> getAllPlansById(Long id);

    PlanModel getPlan(Long id);

    PlanModel createPlan(CreatePlanModel plan);

    PlanModel updatePlan(UpdatePlanModel plan, Long id);

    void deletePlan(Long id);

}
