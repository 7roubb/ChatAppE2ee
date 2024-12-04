package com.cotede.e2eechatapp.users;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Node("FriendShipRequest")
public class FriendShipRequest {
    @Id
    @GeneratedValue
    private Long id;

    private User sender;

    private User receiver;

    private LocalDateTime sendAt;

    @Builder.Default
    private boolean accepted = false;

    public void accept() {
        accepted = true;
    }
    public void reject() {
        accepted = false;
    }

    public boolean isAccepted() {
        return accepted;
    }


}
