package rs.ac.singidunum.workout.services.users;


import org.springframework.stereotype.Service;
import rs.ac.singidunum.workout.exceptions.UserNotFoundException;
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
        return userRepository.findAll();
    }

    @Override
    public UserModel createUser(UserModel userModel) {
        return userRepository.save(userModel);
    }

    @Override
    public UserModel getUser(Long userId){
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("No such user exists"));
    }

    @Override
    public UserModel findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


}
