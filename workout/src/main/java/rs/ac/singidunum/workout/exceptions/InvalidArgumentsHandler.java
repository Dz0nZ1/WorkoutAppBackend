package rs.ac.singidunum.workout.exceptions;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import java.util.HashMap;
import java.util.Map;


public class InvalidArgumentsHandler {

    public Map<String, String> getErrorMessages(BindingResult bindingResult) {
        Map<String, String> errorMap = new HashMap<>();
        bindingResult.getAllErrors().forEach(error -> {
            var defaultMessage = error.getDefaultMessage();
            if( error instanceof FieldError) {
                var fieldError = (FieldError) error;
             errorMap.put(fieldError.getField(), defaultMessage );
            }else {
                errorMap.put("Error: ", defaultMessage);
            }

        });
        return errorMap;
    }

}
