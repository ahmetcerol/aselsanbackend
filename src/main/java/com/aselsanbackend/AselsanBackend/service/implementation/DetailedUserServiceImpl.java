package com.aselsanbackend.AselsanBackend.service.implementation;

import com.aselsanbackend.AselsanBackend.entity.DetailedUser;
import com.aselsanbackend.AselsanBackend.repository.DetailedUserRepository;
import com.aselsanbackend.AselsanBackend.service.DetailedUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor

public class DetailedUserServiceImpl implements DetailedUserService {

    private final DetailedUserRepository detailedUserRepository;


    @Override
    public DetailedUser getByUserTcKimlikNo(String tcKimlikNo) {
        return detailedUserRepository.findByUserTcKimlikNo(tcKimlikNo);

    }

    @Override
    public DetailedUser saveDetailedUser(DetailedUser detailedUser) {
        detailedUserRepository.save(detailedUser);
        return detailedUser;
    }

    @Override
    public boolean isuserActive(String tcKimlikNo) {
        DetailedUser detailedUser = detailedUserRepository.findByKimlikNo(tcKimlikNo);
        if (detailedUser != null && detailedUser.isAktif()) {
            return true;
        }
        return false;
    }
}
