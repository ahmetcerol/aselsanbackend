package com.aselsanbackend.AselsanBackend.service.implementation;

import com.aselsanbackend.AselsanBackend.dto.UserDto;
import com.aselsanbackend.AselsanBackend.entity.KariyerHedeflerim;
import com.aselsanbackend.AselsanBackend.entity.User;
import com.aselsanbackend.AselsanBackend.repository.KariyerHedeflerimRepository;
import com.aselsanbackend.AselsanBackend.repository.UserRepository;
import com.aselsanbackend.AselsanBackend.security.PasswordHasher;
import com.aselsanbackend.AselsanBackend.service.KariyerHedeflerimService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KariyerHedeflerimServiceImpl implements KariyerHedeflerimService {

    private final UserRepository userRepository;
    private final KariyerHedeflerimRepository kariyerHedeflerimRepository;
    @Override
    public KariyerHedeflerim save( KariyerHedeflerim kariyerHedeflerim) {
        return kariyerHedeflerimRepository.save(kariyerHedeflerim);
    }
}
