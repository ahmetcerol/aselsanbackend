package com.aselsanbackend.AselsanBackend.service;

import com.aselsanbackend.AselsanBackend.dto.UserDto;
import com.aselsanbackend.AselsanBackend.entity.User;
import com.aselsanbackend.AselsanBackend.entity.İlgiAlanları;

import java.util.List;

public interface İlgiAlanlarıService {

    UserDto save(String tcKimlikno, List<String> yeniAdresler);


}
