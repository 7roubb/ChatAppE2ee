package com.cotede.e2eechatapp.users;

import com.cotede.e2eechatapp.common.BaseEntity;
import jakarta.persistence.Column;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Node("Role")

public class Role extends BaseEntity {
    @Id
    @GeneratedValue
    private Long uuid;

    @Column(unique = true)
    private String name;


    @Builder.Default
    @Relationship(type = "HAS_ROLE", direction = Relationship.Direction.INCOMING)
    private Set<User> users = new HashSet<>();

}
