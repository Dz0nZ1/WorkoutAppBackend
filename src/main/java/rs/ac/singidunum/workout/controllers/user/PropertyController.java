package rs.ac.singidunum.workout.controllers.user;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import rs.ac.singidunum.workout.models.workouts.PropertyModel;
import rs.ac.singidunum.workout.services.properties.PropertyService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@PreAuthorize("hasRole('User')")
@RequestMapping("/api/v1/properties")
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<List<PropertyModel>> getAllProperties(){
        return new ResponseEntity<>(propertyService.getAllProperties(), HttpStatus.OK);
    }


    @GetMapping("/get/{name}")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<List<PropertyModel>> getAllPropertiesByPlan(@PathVariable("name") String name) {
        return new ResponseEntity<>(propertyService.findAllPropertiesFromExercise(name), HttpStatus.OK);
    }


//    @GetMapping("/get/{id}")
//    @PreAuthorize("hasAuthority('user:read')")
//    public ResponseEntity<PropertyModel> getProperty(@PathVariable("id") Long id) {
//        return new ResponseEntity<>(propertyService.getProperty(id), HttpStatus.OK);
//    }


    @PostMapping("/create")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<PropertyModel> createProperty(@Valid @RequestBody PropertyModel property, BindingResult result) {
        return new ResponseEntity<>(propertyService.createProperty(property), HttpStatus.OK);
    }


    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<PropertyModel> updateProperty(@Valid @RequestBody PropertyModel property, @PathVariable("id") Long id, BindingResult result) {
        return new ResponseEntity<>(propertyService.updateProperty(property, id), HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('user:delete')")
    public ResponseEntity<Map<String, String>> deleteProperty(@PathVariable("id") Long planId) {
        propertyService.deleteProperty(planId);
        Map<String, String> response = new HashMap<>();
        response.put("defaultMessage", "Property was deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
