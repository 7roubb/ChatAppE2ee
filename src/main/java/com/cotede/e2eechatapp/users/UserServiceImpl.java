package com.cotede.e2eechatapp.users;

import com.cotede.e2eechatapp.exceptions.CustomExceptions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO){
        User existingUserByUsername = userRepository.findByUsername(userRequestDTO.getUserName());
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
        Optional<User> user = Optional.ofNullable(userRepository.findByUsername(username));
        return user.map(UserMapper::toUserResponse)
                .orElseThrow(()
                        -> new CustomExceptions.UserNotFound(username));
    }

    @Override
    public UserResponseDTO updateUser(UserRequestDTO userRequestDTO) {
        Optional<User> existingUserOptional = Optional.ofNullable(userRepository.findByUsername(userRequestDTO.getUserName()));
        User existingUser = existingUserOptional.orElseThrow(() ->
                new CustomExceptions.UserNotFound(userRequestDTO.getUserName())
        );
        Optional.ofNullable(userRequestDTO.getNewUserName())
                .ifPresent(existingUser::setUsername);
        Optional.ofNullable(userRequestDTO.getPassword())
                .ifPresent(existingUser::setPassword);
        Optional.ofNullable(userRequestDTO.getEmail())
                .ifPresent(existingUser::setEmail);
        userRepository.save(existingUser);
        return UserMapper.toUserResponse(existingUser);
    }
    public boolean deleteUserByUserName(String userName) {
        long deletedCount = userRepository.deleteByUsername(userName);
        return deletedCount > 0;
    }

    @Override
    public Boolean deleteUser(String username){
        return deleteUserByUserName(username);
    }

    @Override
    @Transactional
    public void addFriend(String userName, String friendUserName) {
        User user = Optional.ofNullable(userRepository.findByUsername(userName))
                .orElseThrow(() -> new CustomExceptions.UserNotFound(userName));

        User friend = Optional.ofNullable(userRepository.findByUsername(friendUserName))
                .orElseThrow(() -> new CustomExceptions.UserNotFound(friendUserName));
        if (user.getFriends().contains(friend)) {
            throw new CustomExceptions.FriendAlreadyExistsException(friendUserName);
        }
        user.sendFriendshipRequest(friend);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void removeFriend(String userName, String friendUserName) {
        User user = Optional.ofNullable(userRepository.findByUsername(userName))
                .orElseThrow(() -> new CustomExceptions.UserNotFound(userName));
        User friend = Optional.ofNullable(userRepository.findByUsername(friendUserName))
                .orElseThrow(() -> new CustomExceptions.UserNotFound(friendUserName));
        if (!user.getFriends().contains(friend)) {
            throw new CustomExceptions.FriendNotFoundException(friendUserName);
        }
        user.removeFriend(friend);
        userRepository.save(user);
    }

    @Override
    public void acceptFriend(String userId, String friendUserName) {
        User user = Optional.ofNullable(userRepository.findByUsername(userId))
                .orElseThrow(() -> new CustomExceptions.UserNotFound(userId));
        User friend = Optional.ofNullable(userRepository.findByUsername(friendUserName))
                .orElseThrow(() -> new CustomExceptions.UserNotFound(friendUserName));
        FriendShipRequest request = user.getIncomingRequests().stream()
                .filter(r -> r.getSender().equals(friend) && !r.isAccepted())
                .findFirst()
                .orElseThrow(() -> new CustomExceptions.RequestNotFoundException(userId));

        user.acceptFriendshipRequest(request);
        userRepository.save(user);

    }
}
