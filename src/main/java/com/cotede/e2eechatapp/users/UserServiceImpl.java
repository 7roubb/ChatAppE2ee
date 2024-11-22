package com.cotede.e2eechatapp.users;

import com.cotede.e2eechatapp.exceptions.CustomExceptions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO){
        User user = UserMapper.toUser(userRequestDTO);
        userRepository.save(user);
        return UserMapper.toUserResponse(user);

    }

    @Override
    public UserResponseDTO getUser(String username){
        Optional<User> user = Optional.ofNullable(userRepository.findByUserName(username));
        return user.map(UserMapper::toUserResponse)
                .orElseThrow(()
                        -> new CustomExceptions.UserNotFound(username));
    }

    @Override
    public UserResponseDTO updateUser(UserRequestDTO userRequestDTO) {
        return userRepository.findById(userRequestDTO.getId())
                .map(existingUser -> {
                    Optional.ofNullable(userRequestDTO.getUserName()).ifPresent(existingUser::setUserName);
                    Optional.ofNullable(userRequestDTO.getPassword()).ifPresent(existingUser::setPassword);
                    Optional.ofNullable(userRequestDTO.getEmail()).ifPresent(existingUser::setEmail);
                    userRepository.save(existingUser);

                    return UserMapper.toUserResponse(existingUser);
                })
                .orElseThrow(() -> new CustomExceptions.UserNotFound("User not found with ID: " + userRequestDTO.getId()));
    }

    @Override
    public Boolean deleteUser(String username){
        return userRepository.deleteByUserName(username);
    }
}