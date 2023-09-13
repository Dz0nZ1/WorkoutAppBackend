package rs.ac.singidunum.workout.controllers.user;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import rs.ac.singidunum.workout.exceptions.InvalidArgumentsHandler;
import rs.ac.singidunum.workout.exceptions.UserNotFoundException;
import rs.ac.singidunum.workout.models.auth.UserModel;
import rs.ac.singidunum.workout.services.users.UserService;

import java.util.List;

@RestController
@PreAuthorize("hasRole('User')")
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/home")
    @PreAuthorize("hasAuthority('user:read')")
    public String home(){
        return "Home page for user";
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<List<UserModel>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('user:create')")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserModel userModel, BindingResult result){

        if(result.hasErrors()) {
            var exceptionHandler = new InvalidArgumentsHandler();
            return new ResponseEntity<>(exceptionHandler.getErrorMessages(result), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userService.createUser(userModel), HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<?> getUser(@PathVariable("id") Long userId) throws UserNotFoundException {
        return new ResponseEntity<>(userService.getUser(userId), HttpStatus.OK);
    }

}
