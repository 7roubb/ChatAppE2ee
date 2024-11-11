package users;

import exceptions.CustomExceptions;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO){
        User user = UserMapper.toUser(userRequestDTO);
        userRepository.save(user);
        return UserMapper.toUserResponse(user);

    }

    @Override
    public UserResponseDTO getUser(String username){
        User user = userRepository.findByUsername(username);
        return UserMapper.toUserResponse(user);
    }

    @Override
    @Transactional
    public UserResponseDTO updateUser(UserRequestDTO userRequestDTO) {
        return userRepository.findById(userRequestDTO.getId())
                .map(existingUser -> {
                    Optional.ofNullable(userRequestDTO.getUsername()).ifPresent(existingUser::setUsername);
                    Optional.ofNullable(userRequestDTO.getPassword()).ifPresent(existingUser::setPassword);
                    Optional.ofNullable(userRequestDTO.getEmail()).ifPresent(existingUser::setEmail);
                    existingUser.setUpdatedAt(LocalDateTime.now());
                    userRepository.save(existingUser);

                    return UserMapper.toUserResponse(existingUser);
                })
                .orElseThrow(() -> new CustomExceptions.UserNotFound("User not found with ID: " + userRequestDTO.getId()));
    }

    @Override
    public Boolean deleteUser(String username){
        return userRepository.deleteByUsername(username);
    }
}