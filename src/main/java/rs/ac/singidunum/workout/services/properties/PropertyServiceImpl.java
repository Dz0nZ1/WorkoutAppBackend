package rs.ac.singidunum.workout.services.properties;

import org.springframework.stereotype.Service;
import rs.ac.singidunum.workout.models.workouts.PropertyModel;
import rs.ac.singidunum.workout.repositories.PropertiesRepository;

import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {

    private final PropertiesRepository propertiesRepository;

    public PropertyServiceImpl(PropertiesRepository propertiesRepository) {
        this.propertiesRepository = propertiesRepository;
    }

    @Override
    public List<PropertyModel> getAllProperties() {
        return propertiesRepository.findAll();
    }


    @Override
    public List<PropertyModel> findAllPropertiesFromPlan(String fromPlane) {
        return propertiesRepository.findAllByForPlan(fromPlane);
    }

    @Override
    public PropertyModel getProperty(Long propertyId) {
        return propertiesRepository.findById(propertyId).orElseThrow(null);
    }

    @Override
    public PropertyModel createProperty(PropertyModel property) {
        return propertiesRepository.save(property);
    }

    @Override
    public PropertyModel updateProperty(PropertyModel property, Long propertyId) {
        var newProperty = propertiesRepository.findById(propertyId).orElseThrow(null);
        newProperty.setReps(property.getReps());
        newProperty.setSets(property.getSets());
        newProperty.setWeight(property.getWeight());
        propertiesRepository.save(newProperty);
        return newProperty;
    }

    @Override
    public void deleteProperty(Long propertyId) {
        propertiesRepository.deleteById(propertyId);
    }
}
