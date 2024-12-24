package com.cotede.e2eechatapp.users;

import com.cotede.e2eechatapp.common.BaseEntity;
import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Node("User")
public class User extends BaseEntity implements UserDetails, Principal {

    @Id
    @GeneratedValue
    private Long uuid;

    private String username;

    @Column(unique = true)
    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    private Boolean enabled;

    private Boolean accountLocked;

    @Builder.Default
    @Relationship(type = "HAS_ROLE", direction = Relationship.Direction.OUTGOING)
    private Set<Role> roles =new HashSet<Role>();

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

    @Override
    public String getName() {
        return firstName + " " + lastName;
    }

    public void removeFriend(User friend){
        this.friends.remove(friend);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    private String getFullName() {
        return firstName + " " + lastName;
    }
}
