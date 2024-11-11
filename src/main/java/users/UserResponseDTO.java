package users;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDTO {
    private String id;
    private String name;
    private String email;
    private String fullname;
}
