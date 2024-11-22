package com.cotede.e2eechatapp.users;

import com.cotede.e2eechatapp.common.OnUpdate;
import com.cotede.e2eechatapp.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;
    private final MessageSource messageSource;

    @PostMapping("/create")
    public ApiResponse<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequestDTO) {
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
    public ResponseEntity<UserResponseDTO> updateUser(@Validated(OnUpdate.class) @RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO updatedUser = userService.updateUser(userRequestDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@RequestHeader String username) {
        Boolean isDeleted = userService.deleteUser(username);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
