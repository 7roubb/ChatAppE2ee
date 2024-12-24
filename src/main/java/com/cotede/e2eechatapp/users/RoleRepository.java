package com.cotede.e2eechatapp.users;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Optional;

public interface RoleRepository extends Neo4jRepository<Role,Long> {
    Optional<Role> findByName(String name);
}
