package com.aselsanbackend.AselsanBackend.service;

import com.aselsanbackend.AselsanBackend.dto.UserDto;
import com.aselsanbackend.AselsanBackend.entity.StajBilgileri;

import java.util.List;

public interface StajBilgileriService {
    UserDto save (String tcKimlikno, List<StajBilgileri> stajBilgileri);
}
