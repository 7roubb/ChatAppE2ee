package com.cotede.e2eechatapp.users;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends Neo4jRepository<User,Long> {
 User findByUsername(String username);
 long deleteByUsername(String username);

 Optional<Object> findByEmail(String email);
}
