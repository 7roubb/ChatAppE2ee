package com.cotede.e2eechatapp.users;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
public interface UserRepository extends MongoRepository<User, String> {
 User findByUserName(String username);
 Boolean deleteByUserName(String username);
}
