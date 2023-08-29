package rs.ac.singidunum.workout.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import rs.ac.singidunum.workout.exceptions.InvalidArgumentsHandler;
import rs.ac.singidunum.workout.models.AuthenticationRequestModel;
import rs.ac.singidunum.workout.models.RegisterRequestModel;
import rs.ac.singidunum.workout.services.AuthenticationService;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    @CrossOrigin("*")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequestModel request, BindingResult result){

        if(result.hasErrors()) {
            var exceptionHandler = new InvalidArgumentsHandler();
            return new ResponseEntity<>(exceptionHandler.getErrorMessages(result), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(authenticationService.register(request), HttpStatus.OK);
    }

    @PostMapping("/login")
    @CrossOrigin("*")
    public ResponseEntity<?> login(@Valid @RequestBody AuthenticationRequestModel request, BindingResult result){

        if(result.hasErrors()) {
            var exceptionHandler = new InvalidArgumentsHandler();
            return new ResponseEntity<>(exceptionHandler.getErrorMessages(result), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(authenticationService.authenticate(request), HttpStatus.OK);
    }

    @PostMapping("/refresh-token")
    @CrossOrigin("*")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        authenticationService.refreshToken(request, response);
    }

}
