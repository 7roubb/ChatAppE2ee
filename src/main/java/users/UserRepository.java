package users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, Long> {
 User findByUsername(String username);
 Boolean deleteByUsername(String username);
}
