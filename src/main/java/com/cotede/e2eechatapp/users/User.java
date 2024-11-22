package com.cotede.e2eechatapp.users;

import com.cotede.e2eechatapp.common.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Node("User")
public class User extends BaseEntity {

    @Id
    @GeneratedValue
    private Long uuid ;


    private String userName;

    private String email;

    private String password;

    private String fullName;

    @Builder.Default
    @Relationship(type = "FRIEND", direction = Relationship.Direction.OUTGOING)
    private Set<User> friends = new HashSet<>();

    public void addFriend(User friend) {
        if (friend != null) {
            this.friends.add(friend);
        }
    }

    public void removeFriend(User friend) {
        this.friends.remove(friend);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(uuid, user.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

}
