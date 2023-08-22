package com.aselsanbackend.AselsanBackend.service;

import com.aselsanbackend.AselsanBackend.entity.DetailedUser;

public interface DetailedUserService {
    DetailedUser getByUserTcKimlikNo(String tcKimlikNo);

    DetailedUser saveDetailedUser(DetailedUser detailedUser);

    boolean isuserActive(String tcKimlikNo);
}
