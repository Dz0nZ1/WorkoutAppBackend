package rs.ac.singidunum.workout.services.properties;
import rs.ac.singidunum.workout.models.workout.property.CreatePropertyModel;
import rs.ac.singidunum.workout.models.workout.property.PropertyModel;
import rs.ac.singidunum.workout.models.workout.property.UpdatePropertyModel;

import java.util.List;

public interface PropertyService {

    List<PropertyModel> getAllProperties();

    List<PropertyModel> getAllPropertiesFromExercise(String fromPlane);

    List<PropertyModel> getAllPropertiesByPlan(Long planId);

    PropertyModel getProperty(Long propertyId);

    PropertyModel createProperty(CreatePropertyModel property);

    PropertyModel updateProperty(UpdatePropertyModel property, Long propertyId);

    void deleteProperty(Long propertyId);


}
