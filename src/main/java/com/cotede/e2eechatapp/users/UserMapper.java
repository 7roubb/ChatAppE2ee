package com.cotede.e2eechatapp.users;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class UserMapper {

    public static User toUser(UserRequestDTO userRequest) {
       return  Optional.ofNullable(userRequest).map(
                req ->{
                    User user = new User();
                    user.setUsername(userRequest.getUserName());
                    user.setEmail(userRequest.getEmail());
                    user.setPassword(userRequest.getPassword());
                    user.setFirstName(userRequest.getFirstName());
                    user.setLastName(userRequest.getLastName());
                    user.setDateOfBirth(userRequest.getDateOfBirth());
                    user.setEnabled(true);
                    user.setCreatedBy(user.getUsername());
                    return user;
                }
        ).orElse(null);


    }
    public static UserResponseDTO toUserResponse(User user) {
        return Optional.ofNullable(user).map(u ->{
            UserResponseDTO userResponse = new UserResponseDTO();
            userResponse.setUuid(u.getUuid());
            userResponse.setUsername(user.getUsername());
            userResponse.setEmail(user.getEmail());
            userResponse.setFirstName(user.getFirstName());
            userResponse.setLastName(user.getLastName());
            return userResponse;
        }).orElse(null);
    }

}
