package rs.ac.singidunum.workout.services.plan;

import org.springframework.stereotype.Service;
import rs.ac.singidunum.workout.exceptions.PlanNotFoundException;
import rs.ac.singidunum.workout.exceptions.UserNotFoundException;
import rs.ac.singidunum.workout.models.workouts.PlanModel;
import rs.ac.singidunum.workout.repositories.PlanRepository;
import rs.ac.singidunum.workout.repositories.PropertiesRepository;
import rs.ac.singidunum.workout.repositories.UserRepository;

import java.util.List;

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
        return planRepository.findAll();
    }

    @Override
    public List<PlanModel> getAllPlansByName(String name) {
        return planRepository.findAllByName(name);
    }

    @Override
    public List<PlanModel> getAllPlansById(Long id) {
        return planRepository.findAllByIdentity(id);
    }


    @Override
    public PlanModel getPlan(Long id) {
        return planRepository.findById(id).orElseThrow(() -> new PlanNotFoundException("Plan doesn't exist"));
    }

    @Override
    public PlanModel createPlan(PlanModel planModel) {
        planRepository.save(planModel);
        planModel.getProperties().forEach(p -> p.setPlan(planModel));
        propertiesRepository.saveAll(planModel.getProperties());
        var user = userRepository.findById(planModel.getIdentity()).orElseThrow(() -> new UserNotFoundException("User not found"));
        planModel.setUser(user);
        planRepository.save(planModel);
        return planModel;
    }

    @Override
    public PlanModel updatePlan(PlanModel planModel, Long id) {
        var newPlan = planRepository.findById(id).orElseThrow(() -> new PlanNotFoundException("Plan doesn't exist "));
        newPlan.setName(planModel.getName());
        newPlan.setPlanId(newPlan.getPlanId());
        return planRepository.save(newPlan);
    }

    @Override
    public void deletePlan(Long id) {
        planRepository.deleteById(id);
    }
}
