package com.fullstackdeneme.dataAccess;

import com.fullstackdeneme.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByuserName(String userName);
}
