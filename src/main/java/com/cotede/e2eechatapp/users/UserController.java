package com.cotede.e2eechatapp.users;

import com.cotede.e2eechatapp.common.OnCreate;
import com.cotede.e2eechatapp.common.OnUpdate;
import com.cotede.e2eechatapp.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;
    private final MessageSource messageSource;

    @PostMapping
    public ApiResponse<UserResponseDTO> createUser( @Validated(OnCreate.class) @RequestBody @Valid  UserRequestDTO userRequestDTO) {
        UserResponseDTO createdUser = userService.createUser(userRequestDTO);
        String message = messageSource.getMessage("user.create.success",new Object[]{userRequestDTO.getUserName()}, LocaleContextHolder.getLocale());
        return ApiResponse.success(createdUser,HttpStatus.OK,message) ;
    }

    @GetMapping("/{username}")
    public ApiResponse<UserResponseDTO> getUser(@PathVariable String username) {
        UserResponseDTO userResponse = userService.getUser(username);
        String message = messageSource.getMessage("user.get.success",new Object[]{username}, LocaleContextHolder.getLocale());
        return ApiResponse.success(userResponse,HttpStatus.OK,message) ;
    }

    @PutMapping
    public ApiResponse<UserResponseDTO> updateUser(@Validated(OnUpdate.class) @RequestBody @Valid UserRequestDTO userRequestDTO) {
        UserResponseDTO updatedUser = userService.updateUser(userRequestDTO);
        String message = messageSource.getMessage("user.update.success",new Object[]{userRequestDTO.getUserName()}, LocaleContextHolder.getLocale());
        return ApiResponse.success(updatedUser,HttpStatus.OK,message) ;    }

    @DeleteMapping
    public ApiResponse<Boolean> deleteUser(@RequestHeader String userName) {
        Boolean isDeleted = userService.deleteUser(userName);
        String message = messageSource.getMessage("user.delete.success",new Object[]{userName}, LocaleContextHolder.getLocale());
        return ApiResponse.success(isDeleted,HttpStatus.OK,message) ;    }

    @PostMapping("/friend")
    public ApiResponse<Void> addFriend(@RequestHeader String userId, @RequestHeader String friendId) {
        userService.addFriend(userId, friendId);
        String message = messageSource.getMessage("friend.add.success",new Object[]{friendId}, LocaleContextHolder.getLocale());
        return ApiResponse.success(null,HttpStatus.OK,message) ;
    }

    @DeleteMapping("/friend")
    public ApiResponse<Void> removeFriend(@RequestHeader String userId, @RequestHeader String friendId) {
        userService.removeFriend(userId, friendId);
        String message = messageSource.getMessage("friend.remove.success",new Object[]{friendId}, LocaleContextHolder.getLocale());
        return ApiResponse.success(null,HttpStatus.OK,message) ;
    }
    @PostMapping("/friend/accept")
    public ApiResponse<Void> acceptFriend(@RequestHeader String userId, @RequestHeader String friendId) {
        userService.acceptFriend(userId, friendId);
        String message = messageSource.getMessage("friend.add.success",new Object[]{friendId}, LocaleContextHolder.getLocale());
        return ApiResponse.success(null,HttpStatus.OK,message) ;
    }


}
