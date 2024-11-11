package users;
import common.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@NoArgsConstructor
@Document(collation = "users")
@SuperBuilder(toBuilder = true)
public class User extends BaseEntity {
    @Id
    Long Id ;
    String username;
    String email;
    String password;
    String fullName;
}
