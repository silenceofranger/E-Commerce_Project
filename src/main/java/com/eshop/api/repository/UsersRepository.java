package com.eshop.api.repository;

import com.eshop.api.model.User;
import java.util.Optional;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends MongoRepository<User, String> {
  Optional<User> findUserByEmail(String email);
}
