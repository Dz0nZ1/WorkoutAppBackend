package rs.ac.singidunum.workout.services.properties;
import rs.ac.singidunum.workout.entities.workouts.Property;

import java.util.List;

public interface PropertyService {

    List<Property> getAllProperties();

    List<Property> getAllPropertiesFromExercise(String fromPlane);

    List<Property> getAllPropertiesByPlan(Long planId);

    Property getProperty(Long propertyId);

    Property createProperty(Property property);

    Property updateProperty(Property property, Long propertyId);

    void deleteProperty(Long propertyId);


}
