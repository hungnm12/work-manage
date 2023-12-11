package com.example.workmanage.Repository;

import com.example.workmanage.Model.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    boolean existsUserByEmail(String email);
    Optional<User> findByEmail(String email);
}
