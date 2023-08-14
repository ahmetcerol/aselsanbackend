package com.aselsanbackend.AselsanBackend.service;

import com.aselsanbackend.AselsanBackend.dto.UserDto;
import com.aselsanbackend.AselsanBackend.entity.User;

import java.util.List;

public interface UserService {
    UserDto save(UserDto userDto);
    User findByTcKimlikNo (String tcKimlikNo);
    List<UserDto> getAll();
}
