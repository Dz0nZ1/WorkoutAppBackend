package rs.ac.singidunum.workout.services.plan;

import org.springframework.stereotype.Service;
import rs.ac.singidunum.workout.entities.workouts.Exercise;
import rs.ac.singidunum.workout.exceptions.PlanNotFoundException;
import rs.ac.singidunum.workout.exceptions.UserNotFoundException;
import rs.ac.singidunum.workout.mappers.ExerciseMapper;
import rs.ac.singidunum.workout.mappers.PlanMapper;
import rs.ac.singidunum.workout.models.workout.plan.CreatePlanModel;
import rs.ac.singidunum.workout.models.workout.plan.PlanModel;
import rs.ac.singidunum.workout.models.workout.plan.UpdatePlanModel;
import rs.ac.singidunum.workout.repositories.PlanRepository;
import rs.ac.singidunum.workout.repositories.PropertiesRepository;
import rs.ac.singidunum.workout.repositories.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PlanServiceImpl implements PlanService{

    private final PlanRepository planRepository;

    private final PropertiesRepository propertiesRepository;

    private final UserRepository userRepository;

    public PlanServiceImpl(PlanRepository planRepository, PropertiesRepository propertiesRepository, UserRepository userRepository) {
        this.planRepository = planRepository;
        this.propertiesRepository = propertiesRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<PlanModel> getAllPlans() {
        return PlanMapper.mapPlanListToPlanModelList( planRepository.findAll());
    }

    @Override
    public List<PlanModel> getAllPlansByName(String name) {
        return PlanMapper.mapPlanListToPlanModelList(planRepository.findAllByName(name));
    }

    @Override
    public List<PlanModel> getAllPlansById(Long id) {
        return PlanMapper.mapPlanListToPlanModelList(planRepository.findAllByIdentity(id));
    }


    @Override
    public PlanModel getPlan(Long id) {
        var plan = planRepository.findById(id).orElseThrow(() -> new PlanNotFoundException("Plan doesn't exist"));
        return PlanMapper.mapPlanToPlanModel(plan);
    }

    @Override
    public PlanModel createPlan(CreatePlanModel planModel) {
        var plan = PlanMapper.mapCreatePlanModelToPlan(planModel);
        planRepository.save(plan);
        plan.getProperties().forEach(p -> p.setPlan(plan));
        propertiesRepository.saveAll(plan.getProperties());
        var user = userRepository.findById(plan.getIdentity()).orElseThrow(() -> new UserNotFoundException("User not found"));
        plan.setUser(user);
        planRepository.save(plan);
        return PlanMapper.mapPlanToPlanModel(plan);
    }

    @Override
    public PlanModel updatePlan(UpdatePlanModel plan, Long id) {
        var newPlan = planRepository.findById(id).orElseThrow(() -> new PlanNotFoundException("Plan doesn't exist "));
        Set<Exercise> exercises = new HashSet<>();

        if(plan.getExercises() != null) {
            exercises = ExerciseMapper.mapExerciseSetModelToExerciseSet(plan.getExercises());
        }

        if(plan.getName() != null) {
            newPlan.setName(plan.getName());
        }
        if(plan.getExercises() != null) {
            newPlan.setExercises(exercises);
        }
        planRepository.save(newPlan);
        return PlanMapper.mapPlanToPlanModel(newPlan);

    }

    @Override
    public void deletePlan(Long id) {
        planRepository.deleteById(id);
    }
}
