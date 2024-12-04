package com.cotede.e2eechatapp.users;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserResponseDTO getUser(String username);
    UserResponseDTO createUser(UserRequestDTO userRequestDTO);
    UserResponseDTO updateUser(UserRequestDTO userRequestDTO);
    Boolean deleteUser(String username);
    void addFriend(String userName, String friendUserName);
    void removeFriend(String userId, String friendUserName);
    void acceptFriend(String userId, String friendUserName);
}
