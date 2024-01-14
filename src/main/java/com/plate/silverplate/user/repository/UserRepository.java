package com.plate.silverplate.user.repository;

import com.plate.silverplate.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u join fetch u.userProfile where u.email=:email")
    Optional<User> findByEmail(String email);
}
