package com.daildra.daildra.data.repository;

import com.daildra.daildra.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User getByUserUid(String userUid);
    User getByUserId(String userId);
}
