package rs.ac.singidunum.workout.mappers;

import rs.ac.singidunum.workout.entities.workouts.Property;
import rs.ac.singidunum.workout.models.workout.property.CreatePropertyModel;
import rs.ac.singidunum.workout.models.workout.property.PropertyModel;
import rs.ac.singidunum.workout.models.workout.property.UpdatePropertyModel;

import java.util.ArrayList;
import java.util.List;

public class PropertyMapper {


    public static PropertyModel mapPropertyToPropertyModel(Property property) {
        return PropertyModel
                .builder()
                .propertyId(property.getPropertyId())
                .forExercise(property.getForExercise())
                .reps(property.getReps())
                .sets(property.getSets())
                .weight(property.getWeight())
                .build();
    }

    public static Property mapPropertyModelToProperty(PropertyModel propertyModel) {
        return Property
                .builder()
                .propertyId(propertyModel.getPropertyId())
                .forExercise(propertyModel.getForExercise())
                .weight(propertyModel.getWeight())
                .sets(propertyModel.getSets())
                .reps(propertyModel.getReps())
                .build();
    }


    public static Property mapCreatePropertyModelToProperty(CreatePropertyModel propertyModel) {
        return Property
                .builder()
                .forExercise(propertyModel.getForExercise())
                .weight(propertyModel.getWeight())
                .sets(propertyModel.getSets())
                .reps(propertyModel.getReps())
                .build();
    }

    public static Property mapUpdatePropertyModelToProperty(UpdatePropertyModel propertyModel) {
        return Property
                .builder()
                .weight(propertyModel.getWeight())
                .sets(propertyModel.getSets())
                .reps(propertyModel.getReps())
                .build();
    }


    public static List<PropertyModel> mapPropertyListToPropertyListModel(List<Property> properties) {
        List<PropertyModel> modelList = new ArrayList<>();
        for(Property property : properties) {
            modelList.add(mapPropertyToPropertyModel(property));
        }
        return modelList;
    }


}
