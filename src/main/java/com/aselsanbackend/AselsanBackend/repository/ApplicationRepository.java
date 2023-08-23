package com.aselsanbackend.AselsanBackend.repository;

import com.aselsanbackend.AselsanBackend.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    Application findByTcKimlikNo (String tcKimlikNo);

}
