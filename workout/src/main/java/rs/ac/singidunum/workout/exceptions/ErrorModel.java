package rs.ac.singidunum.workout.exceptions;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class ErrorModel {

    Integer statusCode;
    String message;
    Instant time;

}
