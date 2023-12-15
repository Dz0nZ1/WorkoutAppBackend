package rs.ac.singidunum.workout.services.users;


import org.springframework.stereotype.Service;
import rs.ac.singidunum.workout.exceptions.UserNotFoundException;
import rs.ac.singidunum.workout.mappers.UserMapper;
import rs.ac.singidunum.workout.models.auth.CreateUserModel;
import rs.ac.singidunum.workout.models.auth.UserModel;
import rs.ac.singidunum.workout.repositories.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<UserModel> getAllUsers() {
        return UserMapper.mapUsersListToUsersModelList(userRepository.findAll());
    }


    @Override
    public UserModel createUser(CreateUserModel userModel) {
        var user = UserMapper.mapCreateUserModeltoUser(userModel);
        userRepository.save(user);
        return UserMapper.mapUserToUserModel(user);
    }

    @Override
    public UserModel getUser(Long userId){
        var user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("No such user exists"));
        return UserMapper.mapUserToUserModel(user);
    }

    @Override
    public UserModel findByEmail(String email) {
        var user = userRepository.findByEmail(email);
        return UserMapper.mapUserToUserModel(user);
    }


}
