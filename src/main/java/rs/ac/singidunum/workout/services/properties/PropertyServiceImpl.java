package rs.ac.singidunum.workout.services.properties;

import jakarta.el.PropertyNotFoundException;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.workout.exceptions.PlanNotFoundException;
import rs.ac.singidunum.workout.mappers.PropertyMapper;
import rs.ac.singidunum.workout.models.workout.property.CreatePropertyModel;
import rs.ac.singidunum.workout.models.workout.property.PropertyModel;
import rs.ac.singidunum.workout.models.workout.property.UpdatePropertyModel;
import rs.ac.singidunum.workout.repositories.PlanRepository;
import rs.ac.singidunum.workout.repositories.PropertiesRepository;

import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {

    private final PropertiesRepository propertiesRepository;
    private final PlanRepository planRepository;

    public PropertyServiceImpl(PropertiesRepository propertiesRepository, PlanRepository planRepository) {
        this.propertiesRepository = propertiesRepository;
        this.planRepository = planRepository;
    }

    @Override
    public List<PropertyModel> getAllProperties() {
        return PropertyMapper.mapPropertyListToPropertyListModel(propertiesRepository.findAll());
    }


    @Override
    public List<PropertyModel> getAllPropertiesFromExercise(String forExercise) {
        return PropertyMapper.mapPropertyListToPropertyListModel(propertiesRepository.findAllByForExercise(forExercise));
    }

    @Override
    public List<PropertyModel> getAllPropertiesByPlan(Long planId) {
        var plan = planRepository.findById(planId).orElseThrow(() -> new PlanNotFoundException("Plan not found"));
        return PropertyMapper.mapPropertyListToPropertyListModel(propertiesRepository.findAllByPlan(plan));
    }

    @Override
    public PropertyModel getProperty(Long propertyId) {
        var property =  propertiesRepository.findById(propertyId).orElseThrow(() -> new PropertyNotFoundException("Property not found"));
        return PropertyMapper.mapPropertyToPropertyModel(property);
    }

    @Override
    public PropertyModel createProperty(CreatePropertyModel propertyModel) {
        var property = PropertyMapper.mapCreatePropertyModelToProperty(propertyModel);
        propertiesRepository.save(property);
        return PropertyMapper.mapPropertyToPropertyModel(property);
    }

    @Override
    public PropertyModel updateProperty(UpdatePropertyModel propertyModel, Long propertyId) {
        var property = propertiesRepository.findById(propertyId).orElseThrow(null);
        if(propertyModel.getReps() != null) {
            property.setReps(propertyModel.getReps());
        }
        if(propertyModel.getSets() != null) {
            property.setSets(propertyModel.getSets());
        }
        if(propertyModel.getWeight() != null) {
            property.setWeight(propertyModel.getWeight());
        }
        propertiesRepository.save(property);
        return PropertyMapper.mapPropertyToPropertyModel(property);
    }

    @Override
    public void deleteProperty(Long propertyId) {
        propertiesRepository.deleteById(propertyId);
    }
}
