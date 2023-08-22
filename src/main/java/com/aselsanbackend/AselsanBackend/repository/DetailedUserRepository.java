package com.aselsanbackend.AselsanBackend.repository;

import com.aselsanbackend.AselsanBackend.entity.DetailedUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailedUserRepository extends JpaRepository<DetailedUser, Long> {

    DetailedUser findByUserTcKimlikNo(String tcKimlikNo);
    DetailedUser findByKimlikNo (String kimlikNo);

}
