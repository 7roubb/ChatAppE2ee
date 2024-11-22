package com.cotede.e2eechatapp.users;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class UserMapper {

    public static User toUser(UserRequestDTO userRequest) {
       return  Optional.ofNullable(userRequest).map(
                req ->{
                    User user = new User();
                    user.setUserName(userRequest.getUserName());
                    user.setEmail(userRequest.getEmail());
                    user.setPassword(userRequest.getPassword());
                    user.setFullName(userRequest.getFullName());
                    return user;
                }
        ).orElse(null);


    }
    public static UserResponseDTO toUserResponse(User user) {
        return Optional.ofNullable(user).map(u ->{
            UserResponseDTO userResponse = new UserResponseDTO();
            userResponse.setUuid(u.getUuid());
            userResponse.setUsername(user.getUserName());
            userResponse.setEmail(user.getEmail());
            userResponse.setFullName(user.getFullName());
            return userResponse;
        }).orElse(null);
    }

}
