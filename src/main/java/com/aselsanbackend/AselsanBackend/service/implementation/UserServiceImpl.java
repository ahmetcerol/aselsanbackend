package com.aselsanbackend.AselsanBackend.service.implementation;

import com.aselsanbackend.AselsanBackend.dto.InfoDto;
import com.aselsanbackend.AselsanBackend.dto.UserDto;
import com.aselsanbackend.AselsanBackend.entity.*;
import com.aselsanbackend.AselsanBackend.repository.DetailedUserRepository;
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
    private final DetailedUserRepository detailedUserRepository;

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

            List<String> ilgiAlanlari = new ArrayList<>();
            it.getAdresleri().forEach(adres -> {
                ilgiAlanlari.add(adres.getIlgiAlanı());
            });
            userDto.setIlgiAlanları(ilgiAlanlari);


            List<ProjeDeneyimleri> projeDeneyimleriList = new ArrayList<>();
            it.getProjeDeneyimleri().forEach(adres -> {
                ProjeDeneyimleri projeler = new ProjeDeneyimleri();
                projeler.setProjeAdi(adres.getProjeAdi());
                projeler.setProjeKurum(adres.getProjeKurum());
                projeler.setProjeDeadlines(adres.getProjeDeadlines());
                projeler.setProjeDetay(adres.getProjeDetay());
                projeDeneyimleriList.add(projeler);
            });
            userDto.setProjeDeneyimleriList(projeDeneyimleriList);

            List<StajBilgileri> stajBilgileris = new ArrayList<>();
            it.getStajBilgileri().forEach(adres -> {
                StajBilgileri stajBilgisi = new StajBilgileri();
                stajBilgisi.setStajYili(adres.getStajYili());
                stajBilgisi.setStajTürü(adres.getStajTürü());
                stajBilgisi.setStajYeri(adres.getStajYeri());
                stajBilgisi.setStajBölümü(adres.getStajBölümü());
                stajBilgisi.setStajSüresi(adres.getStajSüresi());
                stajBilgileris.add(stajBilgisi);
            });
            userDto.setStajBilgileriList(stajBilgileris);

            List<EğitimBilgilerim> eğitimBilgilerims = new ArrayList<>();
            it.getEğitimBilgilerimList().forEach(eğitim -> {
                EğitimBilgilerim güncelEğitimler = new EğitimBilgilerim();
                güncelEğitimler.setOkulAdi(eğitim.getOkulAdi());
                güncelEğitimler.setStartDate(eğitim.getStartDate());
                güncelEğitimler.setFinishDate(eğitim.getFinishDate());
                eğitimBilgilerims.add(güncelEğitimler);
            });
            userDto.setEğitimBilgilerimList(eğitimBilgilerims);
            usersDtoS.add(userDto);
        });
        return usersDtoS;
    }

    @Override
    public List<InfoDto> getAllNecessary() {
        List<User>  users = userRepository.findAll();
        List<InfoDto> usersDtoS= new ArrayList<>();
        users.forEach(it ->{
            InfoDto userDto = new InfoDto();
            userDto.setAd(it.getAd());
            userDto.setSoyad(it.getSoyad());
            userDto.setEPosta(it.getEPosta());

            List<String> ilgiAlanlari = new ArrayList<>();
            it.getAdresleri().forEach(adres -> {
                ilgiAlanlari.add(adres.getIlgiAlanı());
            });
            userDto.setIlgiAlanları(ilgiAlanlari);


            List<ProjeDeneyimleri> projeDeneyimleriList = new ArrayList<>();
            it.getProjeDeneyimleri().forEach(adres -> {
                ProjeDeneyimleri projeler = new ProjeDeneyimleri();
                projeler.setProjeAdi(adres.getProjeAdi());
                projeler.setProjeKurum(adres.getProjeKurum());
                projeDeneyimleriList.add(projeler);
            });
            userDto.setProjeDeneyimleriList(projeDeneyimleriList);

            List<StajBilgileri> stajBilgileris = new ArrayList<>();
            it.getStajBilgileri().forEach(adres -> {
                StajBilgileri stajBilgisi = new StajBilgileri();
                stajBilgisi.setStajYeri(adres.getStajYeri());
                stajBilgileris.add(stajBilgisi);
            });
            userDto.setStajBilgileriList(stajBilgileris);

            List<EğitimBilgilerim> eğitimBilgilerims = new ArrayList<>();
            it.getEğitimBilgilerimList().forEach(eğitim -> {
                EğitimBilgilerim güncelEğitimler = new EğitimBilgilerim();
                güncelEğitimler.setOkulAdi(eğitim.getOkulAdi());
                eğitimBilgilerims.add(güncelEğitimler);
            });
            userDto.setEğitimBilgilerimList(eğitimBilgilerims);
            usersDtoS.add(userDto);
        });
        return usersDtoS;
    }

    @Override
    public List<StajBilgileri> getStajBilgileri(User user) {
        List<StajBilgileri> stajBilgileriDtoList = new ArrayList<>();
        user.getStajBilgileri().forEach(stajBilgisi -> {
            StajBilgileri stajBilgisiDto = new StajBilgileri();
            stajBilgisiDto.setStajYili(stajBilgisi.getStajYili());
            stajBilgisiDto.setStajTürü(stajBilgisi.getStajTürü());
            stajBilgisiDto.setStajYeri(stajBilgisi.getStajYeri());
            stajBilgisiDto.setStajBölümü(stajBilgisi.getStajBölümü());
            stajBilgisiDto.setStajSüresi(stajBilgisi.getStajSüresi());
            stajBilgileriDtoList.add(stajBilgisiDto);
        });
        return stajBilgileriDtoList;
    }

    @Override
    public List<ProjeDeneyimleri> getProjeDeneyimleri(User user) {
        List<ProjeDeneyimleri> projeDeneyimleriDtoList = new ArrayList<>();
        user.getProjeDeneyimleri().forEach(projeDeneyimi -> {
            ProjeDeneyimleri projeDeneyimiDto = new ProjeDeneyimleri();
            projeDeneyimiDto.setProjeAdi(projeDeneyimi.getProjeAdi());
            projeDeneyimiDto.setProjeKurum(projeDeneyimi.getProjeKurum());
            projeDeneyimiDto.setProjeDeadlines(projeDeneyimi.getProjeDeadlines());
            projeDeneyimiDto.setProjeDetay(projeDeneyimi.getProjeDetay());
            projeDeneyimleriDtoList.add(projeDeneyimiDto);
        });
        return projeDeneyimleriDtoList;

    }

    @Override
    public List<EğitimBilgilerim> getEğitimBilgilerim(User user) {
        List<EğitimBilgilerim> eğitimDtoList = new ArrayList<>();
        user.getEğitimBilgilerimList().forEach(eğitim -> {
            EğitimBilgilerim güncelEğitimler = new EğitimBilgilerim();
            güncelEğitimler.setOkulAdi(eğitim.getOkulAdi());
            güncelEğitimler.setStartDate(eğitim.getStartDate());
            güncelEğitimler.setFinishDate(eğitim.getFinishDate());
            eğitimDtoList.add(güncelEğitimler);
        });
        return eğitimDtoList;
    }

    @Override
    public String deletePersonByTcKimlikNumarasi(String tcKimlikNumarasi) {
        User personsToDelete = userRepository.findByTcKimlikNo(tcKimlikNumarasi);
        userRepository.delete(personsToDelete);
        return "Kişisel Verileriniz Silindi";
    }


    public boolean updatePasswordByTcKimlikNo(String tcKimlikNo, String newPassword) {
        User user = userRepository.findByTcKimlikNo(tcKimlikNo);
        String hashedPassword = PasswordHasher.hashPassword(tcKimlikNo + newPassword);
        if (user != null) {
            user.setPassword(hashedPassword);
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }
}
