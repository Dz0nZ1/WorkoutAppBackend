package rs.ac.singidunum.workout.services.plan;

import rs.ac.singidunum.workout.models.workouts.PlanModel;

import java.util.List;

public interface PlanService {

    List<PlanModel> getAllPlans();

    PlanModel getPlan(Long id);

    PlanModel createPlan(PlanModel planModel);

    PlanModel updatePlan(PlanModel planModel, Long id);

    void deletePlan(Long id);

}