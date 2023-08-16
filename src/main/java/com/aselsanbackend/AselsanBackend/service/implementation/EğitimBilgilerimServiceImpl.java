package com.aselsanbackend.AselsanBackend.service.implementation;

import com.aselsanbackend.AselsanBackend.dto.UserDto;
import com.aselsanbackend.AselsanBackend.entity.EğitimBilgilerim;
import com.aselsanbackend.AselsanBackend.entity.User;
import com.aselsanbackend.AselsanBackend.repository.EğitimBilgilerimRepository;
import com.aselsanbackend.AselsanBackend.repository.UserRepository;
import com.aselsanbackend.AselsanBackend.security.PasswordHasher;
import com.aselsanbackend.AselsanBackend.service.EğitimBilgilerimService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EğitimBilgilerimServiceImpl implements EğitimBilgilerimService {

    private final UserRepository userRepository;
    private final EğitimBilgilerimRepository eğitimBilgilerimRepository;
    @Override
    public UserDto save(String tcKimlikNo, List<EğitimBilgilerim> eğitimBilgilerim) {
        User user = userRepository.findByTcKimlikNo(tcKimlikNo);

        List<EğitimBilgilerim> eğitimBilgilerimList = user.getEğitimBilgilerimList();
        eğitimBilgilerim.forEach(it ->{
            EğitimBilgilerim eskiEğitimler = new EğitimBilgilerim();
            eskiEğitimler.setOkulAdi(it.getOkulAdi());
            eskiEğitimler.setStartDate(it.getStartDate());
            eskiEğitimler.setFinishDate(it.getFinishDate());
            eğitimBilgilerimList.add(eskiEğitimler);
        });
        eğitimBilgilerimRepository.saveAll(eğitimBilgilerimList);
        user.setEğitimBilgilerimList(eğitimBilgilerimList);

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

        List<EğitimBilgilerim> güncelEğitimBilgileri = new ArrayList<>();
        kisiDb.getEğitimBilgilerimList().forEach(eğitim -> {
            EğitimBilgilerim güncelEğitimler = new EğitimBilgilerim();
            güncelEğitimler.setOkulAdi(eğitim.getOkulAdi());
            güncelEğitimler.setStartDate(eğitim.getStartDate());
            güncelEğitimler.setFinishDate(eğitim.getFinishDate());
            güncelEğitimBilgileri.add(güncelEğitimler);
        });
        kisiDto.setEğitimBilgilerimList(güncelEğitimBilgileri);
        return kisiDto;
    }
}
