package rs.ac.singidunum.workout.services.users;

import rs.ac.singidunum.workout.exceptions.UserNotFoundException;
import rs.ac.singidunum.workout.models.auth.CreateUserModel;
import rs.ac.singidunum.workout.models.auth.UserModel;

import java.util.List;

public interface UserService {

    List<UserModel> getAllUsers();

    UserModel createUser(CreateUserModel userModel);

    UserModel getUser (Long userId) throws UserNotFoundException;

    UserModel findByEmail(String email);
}
