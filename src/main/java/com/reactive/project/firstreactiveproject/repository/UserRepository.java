package com.reactive.project.firstreactiveproject.repository;

import com.reactive.project.firstreactiveproject.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import java.util.UUID;

/**
 * @author Maher FATTOUCH
 * @created 12/04/2021
 */
public interface UserRepository extends ReactiveMongoRepository<User, UUID> {
}
