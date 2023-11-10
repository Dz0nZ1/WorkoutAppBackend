package rs.ac.singidunum.workout.services.plan;

import rs.ac.singidunum.workout.models.workouts.PlanModel;

import java.util.List;

public interface PlanService {

    List<PlanModel> getAllPlans();

    List<PlanModel> getAllPlansByName(String name);

    List<PlanModel> getAllPlansById(Long id);

    PlanModel getPlan(Long id);

    PlanModel createPlan(PlanModel planModel);

    PlanModel updatePlan(PlanModel planModel, Long id);

    void deletePlan(Long id);

}
