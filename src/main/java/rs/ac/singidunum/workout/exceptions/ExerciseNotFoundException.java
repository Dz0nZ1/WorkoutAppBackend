package rs.ac.singidunum.workout.exceptions;

public class ExerciseNotFoundException extends RuntimeException{

    public ExerciseNotFoundException(String message){
        super(message);
    }
}
