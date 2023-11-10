package rs.ac.singidunum.workout.services.properties;
import rs.ac.singidunum.workout.models.workouts.PropertyModel;
import java.util.List;

public interface PropertyService {

    List<PropertyModel> getAllProperties();

    List<PropertyModel> findAllPropertiesFromPlan(String fromPlane);

    PropertyModel getProperty(Long propertyId);

    PropertyModel createProperty(PropertyModel property);

    PropertyModel updateProperty(PropertyModel property, Long propertyId);

    void deleteProperty(Long propertyId);


}
