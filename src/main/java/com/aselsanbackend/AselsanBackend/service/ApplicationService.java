package com.aselsanbackend.AselsanBackend.service;

import com.aselsanbackend.AselsanBackend.entity.Application;

public interface   ApplicationService {

    Application updateApplicationStatus (Application application);
    Application findByTcKimlikNo (String tcKimlikNo);

}
