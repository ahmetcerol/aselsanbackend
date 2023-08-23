package com.aselsanbackend.AselsanBackend.service.implementation;

import com.aselsanbackend.AselsanBackend.entity.Application;
import com.aselsanbackend.AselsanBackend.entity.User;
import com.aselsanbackend.AselsanBackend.repository.ApplicationRepository;
import com.aselsanbackend.AselsanBackend.repository.UserRepository;
import com.aselsanbackend.AselsanBackend.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;



    @Override
    public Application updateApplicationStatus(Application application) {
        return applicationRepository.save(application);
    }

    @Override
    public Application findByTcKimlikNo(String tcKimlikNo) {
        return applicationRepository.findByTcKimlikNo(tcKimlikNo);

    }
}
