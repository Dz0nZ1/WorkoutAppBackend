package rs.ac.singidunum.workout.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Map<String, String> handleRegisterException() {
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("defaultMessage","User with that name already exists");
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public Map<String,String> handleLoginException() {
        Map<String,String> errorResponse = new HashMap<>();
        errorResponse.put("defaultMessage","No such user exists");
        return errorResponse;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(BadCredentialsException.class)
    public Map<String, String> handleBadCredentialsException() {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("defaultMessage", "Wrong password");
        return errorResponse;
    }




    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorModel> handleUserNotFoundException(UserNotFoundException ex) {
        var error =  ErrorModel.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .time(Instant.now())
                .build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(ExerciseNotFoundException.class)
    public ResponseEntity<ErrorModel> handleExerciseNotFoundException(ExerciseNotFoundException ex) {
        var error =  ErrorModel.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .time(Instant.now())
                .build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(PlanNotFoundException.class)
    public ResponseEntity<ErrorModel> handlePlanNotFoundException(PlanNotFoundException ex) {
        var error =  ErrorModel.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .time(Instant.now())
                .build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

    }
}
