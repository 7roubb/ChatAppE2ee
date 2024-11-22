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
        User existingUserByUsername = userRepository.findByUserName(userRequestDTO.getUserName());
        if (existingUserByUsername != null) {
            throw new CustomExceptions.UserAlreadyExistsException(userRequestDTO.getUserName());
        }
        userRepository.findByEmail(userRequestDTO.getEmail())
                .ifPresent(user -> {
                    throw new CustomExceptions.EmailAlreadyExistsException(userRequestDTO.getEmail());
                });
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
        return userRepository.findById(userRequestDTO.getUuid())
                .map(existingUser -> {
                    Optional.ofNullable(userRequestDTO.getUserName()).ifPresent(existingUser::setUserName);
                    Optional.ofNullable(userRequestDTO.getPassword()).ifPresent(existingUser::setPassword);
                    Optional.ofNullable(userRequestDTO.getEmail()).ifPresent(existingUser::setEmail);
                    userRepository.save(existingUser);

                    return UserMapper.toUserResponse(existingUser);
                })
                .orElseThrow(() -> new CustomExceptions.UserNotFound("User not found with ID: " + userRequestDTO.getUuid()));
    }
    public boolean deleteUserByUserName(String userName) {
        long deletedCount = userRepository.deleteByUserName(userName);
        return deletedCount > 0;
    }

    @Override
    public Boolean deleteUser(String username){
        return deleteUserByUserName(username);
    }
}