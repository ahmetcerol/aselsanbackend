package com.aselsanbackend.AselsanBackend.service;

import com.aselsanbackend.AselsanBackend.dto.UserDto;
import com.aselsanbackend.AselsanBackend.entity.KariyerHedeflerim;
import com.aselsanbackend.AselsanBackend.entity.User;
import com.aselsanbackend.AselsanBackend.repository.KariyerHedeflerimRepository;

public interface KariyerHedeflerimService {

    KariyerHedeflerim save(KariyerHedeflerim kariyerHedeflerim);


}
