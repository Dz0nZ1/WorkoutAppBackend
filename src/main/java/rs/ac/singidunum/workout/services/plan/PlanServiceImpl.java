package rs.ac.singidunum.workout.services.plan;

import org.springframework.stereotype.Service;
import rs.ac.singidunum.workout.exceptions.PlanNotFoundException;
import rs.ac.singidunum.workout.exceptions.UserNotFoundException;
import rs.ac.singidunum.workout.entities.workouts.Plan;
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
    public List<Plan> getAllPlans() {
        return planRepository.findAll();
    }

    @Override
    public List<Plan> getAllPlansByName(String name) {
        return planRepository.findAllByName(name);
    }

    @Override
    public List<Plan> getAllPlansById(Long id) {
        return planRepository.findAllByIdentity(id);
    }


    @Override
    public Plan getPlan(Long id) {
        return planRepository.findById(id).orElseThrow(() -> new PlanNotFoundException("Plan doesn't exist"));
    }

    @Override
    public Plan createPlan(Plan plan) {
        planRepository.save(plan);
        plan.getProperties().forEach(p -> p.setPlan(plan));
        propertiesRepository.saveAll(plan.getProperties());
        var user = userRepository.findById(plan.getIdentity()).orElseThrow(() -> new UserNotFoundException("User not found"));
        plan.setUser(user);
        planRepository.save(plan);
        return plan;
    }

    @Override
    public Plan updatePlan(Plan plan, Long id) {
        var newPlan = planRepository.findById(id).orElseThrow(() -> new PlanNotFoundException("Plan doesn't exist "));

        if(plan.getName() != null) {
            newPlan.setName(plan.getName());
        }

        planRepository.save(newPlan);

        return newPlan;

    }

    @Override
    public void deletePlan(Long id) {
        planRepository.deleteById(id);
    }
}
