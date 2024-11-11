package users;

import common.OnCreate;
import common.OnUpdate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Builder
public class UserRequestDTO {
    @NotBlank(groups = {OnUpdate.class})
    private Long id;
    @NotBlank(groups = {OnUpdate.class, OnCreate.class}, message = "Username is required")
    private String username;

    @Email(groups = {OnCreate.class},message = "Invalid email format")
    private String email;

    @NotBlank(groups = { OnCreate.class},message = "Password is required")
    private String password;

    @NotBlank(groups = {OnCreate.class},message = "Full name is required")
    private String fullName;

}
