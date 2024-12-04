package com.cotede.e2eechatapp.users;

import com.cotede.e2eechatapp.common.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.time.LocalDateTime;
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
    private Long uuid;

    private String userName;

    private String email;

    private String password;

    private String fullName;

    @Builder.Default
    @Relationship(type = "FRIEND", direction = Relationship.Direction.OUTGOING)
    private Set<User> friends = new HashSet<>();

    @Builder.Default
    @Relationship(type = "PENDING_FRIENDSHIP", direction = Relationship.Direction.OUTGOING)
    private Set<FriendShipRequest> outgoingRequests = new HashSet<>();

    @Builder.Default
    @Relationship(type = "PENDING_FRIENDSHIP", direction = Relationship.Direction.INCOMING)
    private Set<FriendShipRequest> incomingRequests = new HashSet<>();

    public void sendFriendshipRequest(User receiver) {
        FriendShipRequest request = FriendShipRequest.builder()
                .sender(this)
                .receiver(receiver)
                .sendAt(LocalDateTime.now())
                .build();
        this.outgoingRequests.add(request);
        receiver.getIncomingRequests().add(request);
    }

    public void acceptFriendshipRequest(FriendShipRequest request) {
        if (request.getReceiver().equals(this) && !request.isAccepted()) {
            request.accept();
            this.friends.add(request.getSender());
            request.getSender().getFriends().add(this);
        }
    }

    public void rejectFriendshipRequest(FriendShipRequest request) {
        if (request.getReceiver().equals(this) && !request.isAccepted()) {
            request.reject();
            this.outgoingRequests.remove(request);
            request.getSender().getIncomingRequests().remove(request);
        }
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
    public void removeFriend(User friend){
        this.friends.remove(friend);
    }

}
