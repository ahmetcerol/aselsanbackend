package com.aselsanbackend.AselsanBackend.service;

import com.aselsanbackend.AselsanBackend.dto.InfoDto;
import com.aselsanbackend.AselsanBackend.dto.UserDto;
import com.aselsanbackend.AselsanBackend.entity.EğitimBilgilerim;
import com.aselsanbackend.AselsanBackend.entity.ProjeDeneyimleri;
import com.aselsanbackend.AselsanBackend.entity.StajBilgileri;
import com.aselsanbackend.AselsanBackend.entity.User;

import java.util.List;

public interface UserService {
    UserDto save(UserDto userDto);
    User findByTcKimlikNo (String tcKimlikNo);
    List<UserDto> getAll();
    List<InfoDto> getAllNecessary();
    List<StajBilgileri> getStajBilgileri(User user );
    List<ProjeDeneyimleri> getProjeDeneyimleri (User user);
    List<EğitimBilgilerim> getEğitimBilgilerim(User user);
    String deletePersonByTcKimlikNumarasi(String tcKimlikNumarasi);
    boolean updatePasswordByTcKimlikNo(String tcKimlikNo, String newPassword);
}
