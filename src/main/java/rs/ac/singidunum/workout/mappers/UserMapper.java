package rs.ac.singidunum.workout.mappers;

import rs.ac.singidunum.workout.entities.auth.User;
import rs.ac.singidunum.workout.models.auth.CreateUserModel;
import rs.ac.singidunum.workout.models.auth.UpdateUserModel;
import rs.ac.singidunum.workout.models.auth.UserModel;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    public static UserModel mapUserToUserModel(User user) {

        return UserModel.builder()
                .user_id(user.getUser_id())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }


    public static User mapUserModelToUser(UserModel usersModel) {
        return User.builder()
                .user_id(usersModel.getUser_id())
                .firstName(usersModel.getFirstName())
                .lastName(usersModel.getLastName())
                .email(usersModel.getEmail())
                .role(usersModel.getRole())
                .build();
    }



    public static UpdateUserModel mapUserToUpdateUserModel(User user) {
        return UpdateUserModel.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .email(user.getEmail())
                .build();
    }


    public static User mapUpdateUserModelToUser(UpdateUserModel userModel) {
        return User.builder()
                .firstName(userModel.getFirstName())
                .lastName(userModel.getLastName())
                .password(userModel.getPassword())
                .email(userModel.getEmail())
                .build();
    }

    public static CreateUserModel mapUserToCreateUserModel(User user) {
        return CreateUserModel
                .builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    public static User mapCreateUserModeltoUser(CreateUserModel userModel){
        return User
                .builder()
                .firstName(userModel.getFirstName())
                .lastName(userModel.getLastName())
                .password(userModel.getPassword())
                .email(userModel.getEmail())
                .role(userModel.getRole())
                .build();
    }


    public static List<UserModel> mapUsersListToUsersModelList(List<User> users) {
        List<UserModel> modelList = new ArrayList<>();
        for (User entity : users) {
            modelList.add(mapUserToUserModel(entity));
        }
        return modelList;

    }



}
