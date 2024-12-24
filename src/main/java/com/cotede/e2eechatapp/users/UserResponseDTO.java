package com.cotede.e2eechatapp.users;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDTO {
    private Long uuid;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
}
