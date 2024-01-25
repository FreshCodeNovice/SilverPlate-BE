package com.plate.silverplate.userPhysical.repository;

import com.plate.silverplate.user.domain.entity.User;
import com.plate.silverplate.userPhysical.domain.entity.UserPhysical;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserPhysicalRepository extends JpaRepository<UserPhysical, Long> {
    Optional<UserPhysical> findUserPhysicalByUser(User user);
}
