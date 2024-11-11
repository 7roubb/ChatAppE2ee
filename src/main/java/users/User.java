package users;
import common.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Setter
@Getter
@NoArgsConstructor
@Document(collation = "users")
@SuperBuilder(toBuilder = true)
public class User extends BaseEntity {
    @MongoId
    Long Id ;
    String username;
    String email;
    String password;
    String fullName;
}
