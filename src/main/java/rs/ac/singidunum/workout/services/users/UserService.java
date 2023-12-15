package rs.ac.singidunum.workout.services.users;

import rs.ac.singidunum.workout.exceptions.UserNotFoundException;
import rs.ac.singidunum.workout.entities.auth.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User createUser(User user);

    User getUser (Long userId) throws UserNotFoundException;

    User findByEmail(String email);
}
