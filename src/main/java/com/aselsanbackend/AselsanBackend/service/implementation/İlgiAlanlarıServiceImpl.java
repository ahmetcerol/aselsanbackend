package com.aselsanbackend.AselsanBackend.service.implementation;

import com.aselsanbackend.AselsanBackend.dto.UserDto;
import com.aselsanbackend.AselsanBackend.entity.User;
import com.aselsanbackend.AselsanBackend.entity.İlgiAlanları;
import com.aselsanbackend.AselsanBackend.repository.UserRepository;
import com.aselsanbackend.AselsanBackend.repository.İlgiAlanlarıRepository;
import com.aselsanbackend.AselsanBackend.security.PasswordHasher;
import com.aselsanbackend.AselsanBackend.service.İlgiAlanlarıService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class İlgiAlanlarıServiceImpl implements İlgiAlanlarıService {

    private final İlgiAlanlarıRepository i̇lgiAlanlarıRepository;
    private final UserRepository userRepository;

    @Override
    public UserDto save(String tcKimlikno, List<String> yeniAdresler) {
        User kisi = userRepository.findByTcKimlikNo(tcKimlikno);


        List<İlgiAlanları> adresList = kisi.getAdresleri();
        yeniAdresler.forEach(yeniAdres -> {
            İlgiAlanları adres = new İlgiAlanları();
            adres.setIlgiAlanı(yeniAdres);
            adresList.add(adres);
        });
        i̇lgiAlanlarıRepository.saveAll(adresList);

        kisi.setAdresleri(adresList);

        User kisiDb = userRepository.save(kisi);

        UserDto kisiDto = new UserDto();
        kisiDto.setTcKimlikNo(kisiDb.getTcKimlikNo());
        String hashedPassword = PasswordHasher.hashPassword(kisiDb.getTcKimlikNo() + kisiDb.getPassword());
        kisiDto.setPassword(hashedPassword);
        kisiDto.setAd(kisiDb.getAd());
        kisiDto.setEPosta(kisiDb.getEPosta());
        kisiDto.setBirthDate(kisiDb.getBirthDate());
        kisiDto.setSoyad(kisiDb.getSoyad());
        kisiDto.setNationality(kisiDb.getNationality());


        List<String> kisiAdresleri = new ArrayList<>();
        kisiDb.getAdresleri().forEach(adres -> {
            kisiAdresleri.add(adres.getIlgiAlanı());
        });
        kisiDto.setIlgiAlanları(kisiAdresleri);


        return kisiDto;
    }


}
