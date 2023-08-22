package com.aselsanbackend.AselsanBackend.repository;

import com.aselsanbackend.AselsanBackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByTcKimlikNo(String tcKimlikNo);
}
