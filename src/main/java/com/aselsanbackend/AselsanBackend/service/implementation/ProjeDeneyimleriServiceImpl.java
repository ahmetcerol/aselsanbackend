package com.aselsanbackend.AselsanBackend.service.implementation;

import com.aselsanbackend.AselsanBackend.dto.UserDto;
import com.aselsanbackend.AselsanBackend.entity.ProjeDeneyimleri;
import com.aselsanbackend.AselsanBackend.entity.User;
import com.aselsanbackend.AselsanBackend.repository.ProjeDeneyimleriRepository;
import com.aselsanbackend.AselsanBackend.repository.UserRepository;
import com.aselsanbackend.AselsanBackend.security.PasswordHasher;
import com.aselsanbackend.AselsanBackend.service.ProjeDeneyimleriService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjeDeneyimleriServiceImpl implements ProjeDeneyimleriService {
    private final UserRepository userRepository;
    private  final ProjeDeneyimleriRepository projeDeneyimleriRepository;
    @Override
    public UserDto save(String tcKimlikNo, List<ProjeDeneyimleri> projeDeneyimleri) {
        User user = userRepository.findByTcKimlikNo(tcKimlikNo);

        List<ProjeDeneyimleri> projeDeneyimleriList = user.getProjeDeneyimleri();
        projeDeneyimleri.forEach(it -> {
            ProjeDeneyimleri projeler = new ProjeDeneyimleri();
            projeler.setProjeAdi(it.getProjeAdi());
            projeler.setProjeKurum(it.getProjeKurum());
            projeler.setProjeDeadlines(it.getProjeDeadlines());
            projeler.setProjeDetay(it.getProjeDetay());
            projeDeneyimleriList.add(projeler);
        });
        projeDeneyimleriRepository.saveAll(projeDeneyimleriList);

        user.setProjeDeneyimleri(projeDeneyimleriList);

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

        List<ProjeDeneyimleri> güncelProjeDeneyimleri = new ArrayList<>();
        kisiDb.getProjeDeneyimleri().forEach(proje -> {
            ProjeDeneyimleri güncelProjeler = new ProjeDeneyimleri();
            güncelProjeler.setProjeDetay(proje.getProjeDetay());
            güncelProjeler.setProjeKurum(proje.getProjeKurum());
            güncelProjeler.setProjeDeadlines(proje.getProjeDeadlines());
            güncelProjeler.setProjeAdi(proje.getProjeAdi());
            güncelProjeDeneyimleri.add(güncelProjeler);
        });
        kisiDto.setProjeDeneyimleriList(güncelProjeDeneyimleri);
        return kisiDto;
    }
}
