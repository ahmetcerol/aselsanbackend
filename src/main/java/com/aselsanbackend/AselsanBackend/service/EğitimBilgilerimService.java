package com.aselsanbackend.AselsanBackend.service;

import com.aselsanbackend.AselsanBackend.dto.UserDto;
import com.aselsanbackend.AselsanBackend.entity.EğitimBilgilerim;

import java.util.List;

public interface EğitimBilgilerimService {

    UserDto save  (String tcKimlikNo, List<EğitimBilgilerim> eğitimBilgilerim);
}
