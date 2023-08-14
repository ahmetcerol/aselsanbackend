package com.aselsanbackend.AselsanBackend.service.implementation;

import com.aselsanbackend.AselsanBackend.dto.UserDto;
import com.aselsanbackend.AselsanBackend.entity.User;
import com.aselsanbackend.AselsanBackend.repository.UserRepository;
import com.aselsanbackend.AselsanBackend.security.PasswordHasher;
import com.aselsanbackend.AselsanBackend.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    private  PasswordHasher passwordHasher;
    @Override
    @Transactional
    public UserDto save(UserDto userDto) {
        User user  = new User();
        String hashedPassword = PasswordHasher.hashPassword(userDto.getTcKimlikNo() + userDto.getPassword());
        user.setTcKimlikNo(userDto.getTcKimlikNo());
        user.setNationality(userDto.getNationality());
        user.setPassword(hashedPassword);
        user.setAd(userDto.getAd());
        user.setSoyad(userDto.getSoyad());
        user.setEPosta(userDto.getEPosta());
        user.setBirthDate(userDto.getBirthDate());
        userRepository.save(user);

        return userDto;
    }

    @Override
    public User findByTcKimlikNo(String tcKimlikNo) {
        return userRepository.findByTcKimlikNo(tcKimlikNo);
    }

    @Override
    public List<UserDto> getAll() {
        List<User>  users = userRepository.findAll();
        List<UserDto> usersDtoS= new ArrayList<>();
        users.forEach(it ->{
            UserDto userDto = new UserDto();
            userDto.setNationality(it.getNationality());
            userDto.setTcKimlikNo(it.getTcKimlikNo());
            userDto.setPassword(it.getPassword());
            userDto.setAd(it.getAd());
            userDto.setSoyad(it.getSoyad());
            userDto.setEPosta(it.getEPosta());
            userDto.setBirthDate(it.getBirthDate());
            usersDtoS.add(userDto);
        });
        return usersDtoS;
    }
}
