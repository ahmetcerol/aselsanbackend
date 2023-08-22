package com.aselsanbackend.AselsanBackend.service;

import com.aselsanbackend.AselsanBackend.dto.UserDto;
import com.aselsanbackend.AselsanBackend.entity.ProjeDeneyimleri;

import java.util.List;

public interface ProjeDeneyimleriService {

    UserDto save(String tcKimlikNo, List<ProjeDeneyimleri> projeDeneyimleri);
}
