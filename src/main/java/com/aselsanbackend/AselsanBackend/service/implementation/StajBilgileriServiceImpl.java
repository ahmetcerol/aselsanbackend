package com.aselsanbackend.AselsanBackend.service.implementation;

import com.aselsanbackend.AselsanBackend.dto.UserDto;
import com.aselsanbackend.AselsanBackend.entity.StajBilgileri;
import com.aselsanbackend.AselsanBackend.entity.User;
import com.aselsanbackend.AselsanBackend.repository.StajBilgileriRepository;
import com.aselsanbackend.AselsanBackend.repository.UserRepository;
import com.aselsanbackend.AselsanBackend.security.PasswordHasher;
import com.aselsanbackend.AselsanBackend.service.StajBilgileriService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StajBilgileriServiceImpl implements StajBilgileriService {

    private final UserRepository userRepository;
    private final StajBilgileriRepository stajBilgileriRepository;

    @Override
    public UserDto save(String tcKimlikno, List<StajBilgileri> stajBilgileri) {

        User user = userRepository.findByTcKimlikNo(tcKimlikno);

        List<StajBilgileri> stajBilgileriList = user.getStajBilgileri();
        stajBilgileri.forEach(it -> {
            StajBilgileri stajBilgisi = new StajBilgileri();
            stajBilgisi.setStajYili(it.getStajYili());
            stajBilgisi.setStajTürü(it.getStajTürü());
            stajBilgisi.setStajYeri(it.getStajYeri());
            stajBilgisi.setStajBölümü(it.getStajBölümü());
            stajBilgisi.setStajSüresi(it.getStajSüresi());
            stajBilgileriList.add(stajBilgisi);
        });
        stajBilgileriRepository.saveAll(stajBilgileriList);

        user.setStajBilgileri(stajBilgileriList);

        User kisiDb = userRepository.save(user);

        UserDto kisiDto = new UserDto();
        kisiDto.setTcKimlikNo(kisiDb.getTcKimlikNo());
        String hashedPassword = PasswordHasher.hashPassword(kisiDb.getTcKimlikNo() + kisiDb.getPassword());
        kisiDto.setPassword(hashedPassword);
        kisiDto.setAd(kisiDb.getAd());
        kisiDto.setEPosta(kisiDb.getEPosta());
        kisiDto.setBirthDate(kisiDb.getBirthDate());
        kisiDto.setSoyad(kisiDb.getSoyad());
        kisiDto.setNationality(kisiDb.getNationality());

        List<StajBilgileri> güncelStajBilgileri = new ArrayList<>();
        kisiDb.getStajBilgileri().forEach(staj -> {
            StajBilgileri yeniStaj = new StajBilgileri();
            yeniStaj.setStajYili(staj.getStajYili());
            yeniStaj.setStajBölümü(staj.getStajBölümü());
            yeniStaj.setStajSüresi(staj.getStajSüresi());
            yeniStaj.setStajYeri(staj.getStajYeri());
            yeniStaj.setStajTürü(staj.getStajTürü());
            güncelStajBilgileri.add(yeniStaj);

        });
        kisiDto.setStajBilgileriList(güncelStajBilgileri);
        return kisiDto;
    }
}
