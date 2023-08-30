package rs.ac.singidunum.workout.services;

import rs.ac.singidunum.workout.exceptions.UserNotFoundException;
import rs.ac.singidunum.workout.models.UserModel;

import java.util.List;

public interface UserService {

    List<UserModel> getAllUsers();

    UserModel createUser(UserModel userModel);

    UserModel getUser (Long userId) throws UserNotFoundException;

    UserModel findByEmail(String email);
}
