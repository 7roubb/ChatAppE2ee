package com.cotede.e2eechatapp.users;

import com.cotede.e2eechatapp.common.OnCreate;
import com.cotede.e2eechatapp.common.OnUpdate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@RequiredArgsConstructor
@Validated
@Valid
public class UserRequestDTO {

    private Long uuid;

    @NotBlank(groups = {OnUpdate.class, OnCreate.class}, message = "{user.username.required}")
    @Pattern(regexp = "^[a-zA-Z0-9]{3,20}$", message = "{user.username.invalid}", groups = {OnCreate.class, OnUpdate.class})
    private String userName;

    @NotBlank(groups = {OnUpdate.class}, message = "{user.newUsername.required}")
    @Pattern(regexp = "^[a-zA-Z0-9]{3,20}$", message = "{user.newUsername.invalid}", groups = {OnUpdate.class})
    private String newUserName;

    @NotBlank(message = "{user.email.required}", groups = {OnCreate.class})
    @Email(groups = {OnCreate.class}, message = "{user.email.invalid}")
    @Pattern(regexp = "^[\\w.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "{user.email.pattern.invalid}", groups = {OnCreate.class})
    private String email;

    @NotBlank(groups = {OnCreate.class}, message = "{user.password.required}")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "{user.password.weak}",
            groups = {OnCreate.class}
    )
    private String password;

    @NotBlank(groups = {OnCreate.class}, message = "{user.fullname.required}")
    private String fullName;
}
