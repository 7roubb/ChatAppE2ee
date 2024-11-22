package com.cotede.e2eechatapp.users;

import com.cotede.e2eechatapp.common.OnCreate;
import com.cotede.e2eechatapp.common.OnUpdate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserRequestDTO {

    private String id;

    @NotBlank(groups = {OnUpdate.class, OnCreate.class}, message = "Username is required")
    private String userName;

    @Email(groups = {OnCreate.class},message = "Invalid email format")
    private String email;

    @NotBlank(groups = { OnCreate.class},message = "Password is required")
    private String password;

    @NotBlank(groups = {OnCreate.class},message = "Full name is required")
    private String fullName;

}
