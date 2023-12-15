package rs.ac.singidunum.workout.services.users;


import org.springframework.stereotype.Service;
import rs.ac.singidunum.workout.exceptions.UserNotFoundException;
import rs.ac.singidunum.workout.entities.auth.User;
import rs.ac.singidunum.workout.repositories.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUser(Long userId){
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("No such user exists"));
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


}
