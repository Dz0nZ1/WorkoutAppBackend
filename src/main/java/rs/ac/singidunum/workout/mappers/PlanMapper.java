package rs.ac.singidunum.workout.mappers;

import rs.ac.singidunum.workout.entities.workouts.Plan;
import rs.ac.singidunum.workout.models.workout.plan.CreatePlanModel;
import rs.ac.singidunum.workout.models.workout.plan.PlanModel;

import java.util.ArrayList;
import java.util.List;

public class PlanMapper {

    public static PlanModel mapPlanToPlanModel(Plan plan) {

        return PlanModel
                .builder()
                .planId(plan.getPlanId())
                .exercises(plan.getExercises())
                .identity(plan.getIdentity())
                .name(plan.getName())
                .properties(plan.getProperties())
                .user(plan.getUser())
                .build();
    }

    public static Plan mapPlanModelToPlan(PlanModel planModel) {
        return Plan
                .builder()
                .planId(planModel.getPlanId())
                .exercises(planModel.getExercises())
                .identity(planModel.getIdentity())
                .name(planModel.getName())
                .properties(planModel.getProperties())
                .user(planModel.getUser())
                .build();
    }


    public static Plan mapCreatePlanModelToPlan(CreatePlanModel planModel) {
        return Plan
                .builder()
                .exercises(planModel.getExercises())
                .identity(planModel.getIdentity())
                .name(planModel.getName())
                .properties(planModel.getProperties())
                .user(planModel.getUser())
                .build();
    }


    public static List<PlanModel> mapPlanListToPlanModelList( List<Plan> plans) {
        List<PlanModel> modelList = new ArrayList<>();
        for(Plan plan : plans) {
            modelList.add(mapPlanToPlanModel(plan));
        }
        return modelList;
    }



}
